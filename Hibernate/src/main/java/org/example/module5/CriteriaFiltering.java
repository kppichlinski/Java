package org.example.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderRow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CriteriaFiltering {

    private static final Logger logger = LogManager.getLogger(CriteriaFiltering.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = (Join<Object, Object>) customerRoot.fetch("orders", JoinType.INNER);
        orders.fetch("orderRows").fetch("product");
        ParameterExpression<BigDecimal> totalMin = criteriaBuilder.parameter(BigDecimal.class);
        ParameterExpression<BigDecimal> totalMax = criteriaBuilder.parameter(BigDecimal.class);
        ParameterExpression<String> lastname = criteriaBuilder.parameter(String.class);
        ParameterExpression<Collection> ids = criteriaBuilder.parameter(Collection.class);

        criteriaQuery.select(customerRoot).distinct(true)
                // where c.id=:id and o.total > 50
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.or(
                                        criteriaBuilder.like(customerRoot.get("lastname"),
                                                criteriaBuilder.concat(lastname, "%")),
                                        customerRoot.get("id").in(ids)
                                ),
                                criteriaBuilder.between(orders.get("total"), totalMin, totalMax),
                                criteriaBuilder.isNotNull(customerRoot.get("firstname"))
                        )
                );

        TypedQuery<Customer> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(totalMin, new BigDecimal("30.00"));
        query.setParameter(totalMax, new BigDecimal("70.00"));
        query.setParameter(lastname, "S");
        query.setParameter(ids, Arrays.asList(1L, 2L, 4L));

        List<Customer> resultList = query.getResultList();
        for (Customer customer : resultList) {
            logger.info(customer);
            logger.info(customer.getOrders());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
