package org.example.module3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.ProductInCategoryCounterDto;
import org.example.module2.OneToMany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class JPQLResponseMapping {

    private static final Logger logger = LogManager.getLogger(JPQLResponseMapping.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Object[]> query = entityManager.createQuery(
                "select p.category.id, COUNT(p) from Product p group by p.category", Object[].class);

        List<Object[]> resultList = query.getResultList();
        List<ProductInCategoryCounterDto> result = resultList.stream().map(objects ->
                new ProductInCategoryCounterDto((Long) objects[0], (Long) objects[1])).collect(Collectors.toList());
        for (ProductInCategoryCounterDto dto : result) {
            logger.info(dto.getCategoryId() + ", " + dto.getCounter());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
