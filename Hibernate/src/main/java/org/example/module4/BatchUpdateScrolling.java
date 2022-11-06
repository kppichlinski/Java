package org.example.module4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.batch.BatchReview;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.stream.Stream;

public class BatchUpdateScrolling {

    private static final Logger logger = LogManager.getLogger(BatchUpdateScrolling.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        entityManager.getTransaction().begin();

        Stream<BatchReview> batchReviews = entityManager.createQuery(
                        "select r from BatchReview r", BatchReview.class)
                .setHint(QueryHints.HINT_FETCH_SIZE, Integer.MIN_VALUE)
                .getResultStream();

        batchReviews.forEach(batchReview -> {
            batchReview.setContent("Abc 123");
            batchReview.setRating(16);
            logger.info(batchReviews);
        });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
