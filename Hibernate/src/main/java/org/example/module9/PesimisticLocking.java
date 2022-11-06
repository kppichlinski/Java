package org.example.module9;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class PesimisticLocking {

    private static final Logger logger = LogManager.getLogger(PesimisticLocking.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /*Order order = entityManager.find(Order.class, 3L, LockModeType.PESSIMISTIC_READ);
        logger.info(order);*/

        Order order = entityManager.createQuery(
                "select o from Order o " +
                        "where o.id=:id", Order.class)
                .setParameter("id", 3L)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();
        logger.info(order);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
