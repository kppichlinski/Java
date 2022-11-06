package org.example.module4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.batch.BatchReview;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BatchDeleteJPQL {

    private static final Logger logger = LogManager.getLogger(BatchDeleteJPQL.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        entityManager.getTransaction().begin();

        int deleted = entityManager.createQuery("delete from Review r where r.product.id=:id")
                .setParameter("id", 3L)
                .executeUpdate();
        logger.info("deleted: " + deleted);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
