package org.example.module3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Order;
import org.example.entity.OrderRow;
import org.example.module2.OneToMany;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPQLEntityGraph {

    private static final Logger logger = LogManager.getLogger(JPQLEntityGraph.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // EntityGraph<?> entityGraph = entityManager.getEntityGraph("order-rows");

        EntityGraph<?> entityGraph = entityManager.createEntityGraph(Order.class);
        entityGraph.addAttributeNodes("orderRows");
        entityGraph.addAttributeNodes("customer");
        Subgraph<OrderRow> orderRows = entityGraph.addSubgraph("orderRows");
        orderRows.addAttributeNodes("product");

//        Map<String, Object> map = new HashMap<>();
//        map.put("javax.persistence.fetchgraph", entityGraph);

        List<Order> orders = entityManager.createQuery("select o from Order o", Order.class)
                .setHint("javax.persistence.fetchgraph", entityGraph).getResultList();

        for (Order order : orders) {
            logger.info(order);
            for (OrderRow orderRow : order.getOrderRows()) {
                logger.info(orderRow);
                logger.info(orderRow.getProduct());
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
