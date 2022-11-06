package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Attribute;
import org.example.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class DeleteManyToMany {

    private static final Logger logger = LogManager.getLogger(DeleteManyToMany.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // no need to delete all connected attributes (mapping parent)

//        Product product = entityManager.find(Product.class, 5L);
//        entityManager.remove(product);

        // there u need to delete all connections

        Attribute attribute = entityManager.find(Attribute.class, 1L);
        for (Product attributeProduct : new ArrayList<>(attribute.getProducts())) {
            attribute.removeProduct(attributeProduct);
        }
        entityManager.remove(attribute);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
