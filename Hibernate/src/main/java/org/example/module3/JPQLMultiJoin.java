package org.example.module3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.module2.OneToMany;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPQLMultiJoin {

    private static final Logger logger = LogManager.getLogger(JPQLMultiJoin.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Category> query = entityManager.createQuery(
                        "select distinct c from Category c " +
                                "left join fetch c.products p " +
                                "left join fetch p.reviews", Category.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);

        List<Category> resultList = query.getResultList();
        for (Category category : resultList) {
            logger.info(category);
            logger.info(category.getProducts());
            for (Product product : category.getProducts()) {
                logger.info(product.getReviews());
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
