package org.example.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderRow;
import org.example.entity.batch.BatchReview;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class CriteriaJoin {

    private static final Logger logger = LogManager.getLogger(CriteriaJoin.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = (Join<Object, Object>) customerRoot.fetch("orders", JoinType.INNER);
        orders.fetch("orderRows").fetch("product");
        ParameterExpression<Long> id = criteriaBuilder.parameter(Long.class);
        ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);

        criteriaQuery.select(customerRoot).distinct(true)
                // where c.id=:id and o.total > 50
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(customerRoot.get("id"), id),
                                criteriaBuilder.greaterThan(orders.get("total"), total)
                        )
                );

        TypedQuery<Customer> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(id, 1L);
        query.setParameter(total, new BigDecimal("50.00"));

        List<Customer> resultList = query.getResultList();
        for (Customer customer : resultList) {
            logger.info(customer);
            for (Order order : customer.getOrders()) {
                logger.info(order);
                for (OrderRow orderRow : order.getOrderRows()) {
                    logger.info(orderRow);
                    logger.info(orderRow.getProduct());
                }
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
