package org.example.module3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.module2.OneToMany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPQLMoreMultiJoins {
    private static final Logger logger = LogManager.getLogger(JPQLMoreMultiJoins.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Object[]> query = entityManager.createQuery(
                        "select distinct c.id, c.lastname as customer, " +
                                "ca.name as category, SUM(orw.price) as total " +
                                "from Customer c " +
                                "inner join c.orders o " +
                                "inner join o.orderRows orw " +
                                "inner join orw.product p " +
                                "inner join p.category ca " +
                                "group by ca, c " +
                                "having SUM(orw.price) > 50 " +
                                "order by total desc", Object[].class);

        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            logger.info(row[0] + ", \t" + row[1] + ", \t" + row[2] + ", \t" + row[3]);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
