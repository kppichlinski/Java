package org.example.module2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Product;
import org.example.entity.Review;
import org.example.entity.ReviewDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class UpdateOneToMany {

    private static final Logger logger = LogManager.getLogger(UpdateOneToMany.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<ReviewDto> reviewDtos = getUpdatedReviews();

        Product product = entityManager.find(Product.class, 4L);
        for (Review review :
                product.getReviews()) {
            for (ReviewDto reviewDto :
                    reviewDtos) {
                if (reviewDto.getId() != null) {
                    if (review.getId().equals(reviewDto.getId())) {
                        review.setContent(reviewDto.getContent());
                        review.setRating(reviewDto.getRating());
                    }
                } else {
                    // when this is new review (not updated)
                    logger.warn("This is not existing review");
                }
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static List<ReviewDto> getUpdatedReviews() {
        List<ReviewDto> list = new ArrayList<>();
        list.add(ReviewDto.builder()
                .id(16L)
                .content("Nowa treść !")
                .rating(4)
                .build());
        list.add(ReviewDto.builder()
                .id(17L)
                .content("Nowa treść !")
                .rating(4)
                .build());
        list.add(ReviewDto.builder()
                .id(18L)
                .content("Nowa treść !")
                .rating(4)
                .build());
        return list;
    }
}
