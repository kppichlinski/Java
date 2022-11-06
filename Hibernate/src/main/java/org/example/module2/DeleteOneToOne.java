package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeleteOneToOne {

    private static final Logger logger = LogManager.getLogger(DeleteOneToOne.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 3L);
        if (product.getCategory().getProducts().size() == 1) { // cause now it's OneToMany not OneToOne
            entityManager.remove(product.getCategory());
        }
        product.setCategory(null);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
