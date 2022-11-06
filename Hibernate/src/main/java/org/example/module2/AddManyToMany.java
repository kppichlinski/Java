package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Attribute;
import org.example.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddManyToMany {

    private static final Logger logger = LogManager.getLogger(AddManyToMany.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 5L);

        // Attribute attribute = entityManager.find(Attribute.class, 1L);
        Attribute attribute = Attribute.builder()
                .name("COLOR")
                .value("BLACK")
                .build();

        product.addAttributes(attribute);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
