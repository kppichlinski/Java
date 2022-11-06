package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OneToOne {

    private static final Logger logger = LogManager.getLogger(OneToOne.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // now OneToMany

        Product product = entityManager.find(Product.class, 3L);
        logger.info(product);
        logger.info(product.getCategory());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
