package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class OneToManyBidirectional {

    private static final Logger logger = LogManager.getLogger(OneToManyBidirectional.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Review> reviews = entityManager.createQuery("select r from Review r", Review.class).getResultList();
        for (Review review:
                reviews) {
            logger.info(review);
            logger.info(review.getProduct());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
