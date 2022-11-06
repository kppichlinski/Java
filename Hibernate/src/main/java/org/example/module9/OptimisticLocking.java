package org.example.module9;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class OptimisticLocking {

    private static final Logger logger = LogManager.getLogger(OptimisticLocking.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();

        Order order = entityManager.find(Order.class, 3L);
        Order order2 = entityManager2.find(Order.class, 3L);

        order.setTotal(new BigDecimal("22.55"));
        logger.info(order);
        entityManager.getTransaction().commit();
        entityManager.close();

        order2.setTotal(new BigDecimal("22.66"));
        logger.info(order2);
        entityManager2.getTransaction().commit();
        entityManager2.close();
    }
}
