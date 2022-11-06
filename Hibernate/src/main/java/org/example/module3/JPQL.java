package org.example.module3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.module2.OneToMany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPQL {

    private static final Logger logger = LogManager.getLogger(JPQL.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Object[]> query = entityManager.createQuery(
                "select p.category.name, COUNT(p) from Product p group by p.category", Object[].class);
        //query.setParameter("id", 3L);

        List<Object[]> resultList = query.getResultList();
        for (Object[] array : resultList) {
            logger.info(array[0] + ", " + array[1]);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
