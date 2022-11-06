package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OneToOneBidirectional {

    private static final Logger logger = LogManager.getLogger(OneToOneBidirectional.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // now OneToMany

        Category category = entityManager.find(Category.class, 3L);
        logger.info(category);
        logger.info(category.getProducts());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
