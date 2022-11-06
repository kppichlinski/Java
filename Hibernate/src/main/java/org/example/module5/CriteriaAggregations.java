package org.example.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CriteriaAggregations {

    private static final Logger logger = LogManager.getLogger(CriteriaAggregations.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = customerRoot.join("orders", JoinType.INNER);
        Join<Object, Object> orderRows = orders.join("orderRows");
        Join<Object, Object> product = orderRows.join("product");
        Join<Object, Object> category = product.join("category");

        criteriaQuery.multiselect(
                        customerRoot.get("id"),
                        customerRoot.get("lastname"),
                        category.get("name"),
                        criteriaBuilder.sum(orderRows.get("price"))
                )
                .groupBy(category.get("id"), customerRoot.get("id"))
                .orderBy(criteriaBuilder.desc(criteriaBuilder.sum(orderRows.get("price"))))
                .having(criteriaBuilder.greaterThan(criteriaBuilder.sum(orderRows.get("price")), 50));

        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
        List<Object[]> resultList = query.getResultList();
        for (Object[] object : resultList) {
            logger.info(object[0] + ", " + object[1] + ", " + object[2] + ", " + object[3]);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
