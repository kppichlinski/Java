package org.example.module1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Product;
import org.example.types.ProductType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateProduct {
    private static final Logger logger = LogManager.getLogger(CreateProduct.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = Product.builder()
                .name("Car")
                .description("Fast cause red")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .price(new BigDecimal("59999.99"))
                .productType(ProductType.REAL)
                .build();

        logger.info("Product created");

        entityManager.persist(product);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
