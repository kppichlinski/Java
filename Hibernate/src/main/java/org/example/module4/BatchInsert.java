package org.example.module4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.ProductInCategoryCounterDto;
import org.example.entity.batch.BatchReview;
import org.example.module2.OneToMany;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;

public class BatchInsert {

    private static final Logger logger = LogManager.getLogger(BatchInsert.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        entityManager.getTransaction().begin();

        Long lastId = entityManager.createQuery("select max(r.id) from BatchReview r", Long.class).getSingleResult();

        for (long i = 1; i <= 25; i++) {
            if (i % 5 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            entityManager.persist(new BatchReview(lastId + i, "Content", 5, 1L));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
