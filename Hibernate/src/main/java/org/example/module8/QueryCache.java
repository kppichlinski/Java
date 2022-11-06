package org.example.module8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class QueryCache {

    private static final Logger logger = LogManager.getLogger(QueryCache.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Customer> customers = entityManager.createQuery(
                "select c from Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
        logger.info(customers);

        entityManager.getTransaction().commit();
        entityManager.close();


        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        customers = entityManager.createQuery(
                "select c from Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
        logger.info(customers);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
