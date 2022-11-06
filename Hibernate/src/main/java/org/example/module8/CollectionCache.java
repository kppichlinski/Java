package org.example.module8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CollectionCache {

    private static final Logger logger = LogManager.getLogger(CollectionCache.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, 1L);
        logger.info(customer);
        logger.info(customer.getOrders());
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        customer = entityManager.find(Customer.class, 1L);
        logger.info(customer);
        logger.info(customer.getOrders());
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
