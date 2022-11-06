package org.example.module1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UpdateProduct {

    private static final Logger logger = LogManager.getLogger(UpdateProduct.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1L);
        product.setDescription("Nice for starting drivers");
        logger.info("Updated product: " + product);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
