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

public class JPQLAutomaticResponseMapping {

    private static final Logger logger = LogManager.getLogger(JPQLAutomaticResponseMapping.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<ProductInCategoryCounterDto> query = entityManager.createQuery(
                "select new org.example.entity.ProductInCategoryCounterDto(p.category.id, COUNT(p)) " +
                        "from Product p group by p.category", ProductInCategoryCounterDto.class);

        List<ProductInCategoryCounterDto> resultList = query.getResultList();
        for (ProductInCategoryCounterDto dto : resultList) {
            logger.info(dto.getCategoryId() + ", " + dto.getCounter());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
