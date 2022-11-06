package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class OneToMany {
    private static final Logger logger = LogManager.getLogger(OneToMany.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Product> products = entityManager.createQuery("select p from Product p", Product.class).getResultList();
        for (Product product:
             products) {
            logger.info(product);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
