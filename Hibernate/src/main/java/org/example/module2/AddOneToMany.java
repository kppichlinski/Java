package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Product;
import org.example.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddOneToMany {

    private static final Logger logger = LogManager.getLogger(AddOneToMany.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 5L);

//        Review review = Review.builder()
//                .content("Nowa opinia")
//                .rating(5)
//                .build();

        Review review = entityManager.find(Review.class, 15L);
        product.addReview(review);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
