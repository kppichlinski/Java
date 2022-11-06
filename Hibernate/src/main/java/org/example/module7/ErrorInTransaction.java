package org.example.module7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.Product;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ErrorInTransaction {

    private static final Logger logger = LogManager.getLogger(ErrorInTransaction.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Customer customer = entityManager.createQuery("select c from Customer c where id=:id", Customer.class)
                    .setParameter("id", 100L)
                    .getSingleResult();
            logger.info(customer);
            Product product = entityManager.createQuery("select p from Product p where id=:id", Product.class)
                    .setParameter("id", 1L)
                    .getSingleResult();
            logger.info(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive() || entityManager.getTransaction().getRollbackOnly()) {
                entityManager.getTransaction().rollback();
                logger.error("Transaction rollback");
            }
            logger.error(e, e);
        }

        entityManager.close();
    }
}
