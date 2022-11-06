package org.example.module7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ReadOnlyTransaction {

    private static final Logger logger = LogManager.getLogger(ReadOnlyTransaction.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Customer customer = entityManager.createQuery("select c from Customer c where id=:id", Customer.class)
                .setParameter("id", 1L)
                .setHint(QueryHints.HINT_READONLY, true)
                .getSingleResult();
        customer.setUpdated(LocalDateTime.now());
        entityManager.merge(customer);
        logger.info(customer);

        entityManager.close();
    }
}
