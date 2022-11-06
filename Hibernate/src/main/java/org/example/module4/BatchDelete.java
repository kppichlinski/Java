package org.example.module4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.batch.BatchReview;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Stream;

public class BatchDelete {

    private static final Logger logger = LogManager.getLogger(BatchDelete.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        entityManager.getTransaction().begin();

        List<BatchReview> resultList = entityManager.createQuery("select r from BatchReview r " +
                        "where r.productId=:id", BatchReview.class)
                .setParameter("id", 1L)
                .getResultList();

        for (BatchReview batchReview : resultList) {
            logger.info(batchReview);
            entityManager.remove(batchReview);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
