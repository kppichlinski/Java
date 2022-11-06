package org.example.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.example.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ComparingEntities {

    private static final Logger logger = LogManager.getLogger(ComparingEntities.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Customer customer = entityManager.find(Customer.class, 1L);
        logger.info(customer.getOrders());

        /*Order order = Order.builder()
                .total(new BigDecimal("19.99"))
                .created(LocalDateTime.now())
                .customer(customer)
                .build();
        entityManager.persist(order);*/

        entityManager.clear();

        Order order = entityManager.find(Order.class, 4L);
        logger.info(order.equals(getOrder(customer, 4L)));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static Order getOrder(Customer customer, long id) {
        return customer.getOrders().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
