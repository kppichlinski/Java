package org.example.module4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.batch.BatchReview;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.stream.Stream;

public class BatchUpdateJPQL {

    private static final Logger logger = LogManager.getLogger(BatchUpdateJPQL.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        entityManager.getTransaction().begin();

        int updated = entityManager.createQuery("update Review r SET rating=:rating " +
                "where r.product.id=:id")
                .setParameter("rating", 6)
                .setParameter("id", 1L)
                .executeUpdate();
        logger.info("updated: " + updated);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
