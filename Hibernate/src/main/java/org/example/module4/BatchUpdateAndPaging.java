package org.example.module4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.batch.BatchReview;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BatchUpdateAndPaging {

    private static final Logger logger = LogManager.getLogger(BatchUpdateAndPaging.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Long count = entityManager.createQuery("select count(r) from BatchReview r", Long.class).getSingleResult();

        int batchSize = 10;
        entityManager.unwrap(Session.class).setJdbcBatchSize(batchSize);

        for (int i = 0; i < count; i = i + batchSize) {
            List<BatchReview> batchReviews = entityManager.createQuery(
                            "select r from BatchReview r", BatchReview.class)
                    .setFirstResult(i)
                    .setMaxResults(batchSize)
                    .getResultList();

            logger.info(batchReviews);

            for (BatchReview batchReview : batchReviews) {
                batchReview.setContent("Abc 123");
                batchReview.setRating(12);
            }

            entityManager.flush();
            entityManager.clear();
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
