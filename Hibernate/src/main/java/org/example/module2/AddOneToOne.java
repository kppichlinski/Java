package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Category;
import org.example.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddOneToOne {

    private static final Logger logger = LogManager.getLogger(AddOneToOne.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 3L);
        Category category = Category.builder()
                .name("Nowa kategoria")
                .description("Nowy opis")
                .build();

        entityManager.persist(category);
        product.setCategory(category);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
