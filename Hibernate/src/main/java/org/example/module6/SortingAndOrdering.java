package org.example.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SortingAndOrdering {

    private static final Logger logger = LogManager.getLogger(SortingAndOrdering.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /*Product product = entityManager.find(Product.class, 1L);
        Customer customer = entityManager.find(Customer.class, 1L);

        for (int i = 0; i < 3; i++) {
            Review review = Review.builder()
                    .content("Content " + i)
                    .rating(2 * i)
                    .product(product).build();

            customer.getReviews().add(review);
        }*/

        Customer customer = entityManager.createQuery(
                "select c from Customer c " +
                        "inner join fetch c.reviews r " +
                        "where c.id=:id " +
                        "order by r.id desc", Customer.class)
                .setParameter("id", 1L)
                .getSingleResult();

        customer.getReviews().forEach(logger::info);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static Order getOrder(Customer customer, long id) {
        return customer.getOrders().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
