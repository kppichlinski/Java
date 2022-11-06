package org.example.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.RealProduct;
import org.example.entity.VirtualProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class SingleTableInheritance {

    private static final Logger logger = LogManager.getLogger(SingleTableInheritance.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        VirtualProduct virtualProduct = new VirtualProduct();
        virtualProduct.setName("Virtual");
        virtualProduct.setDescription("virtual description");
        virtualProduct.setCreated(LocalDateTime.now());
        virtualProduct.setDownloadable(true);
        virtualProduct.setFileName("virtual.pdf");
        virtualProduct.setFilePath("/store");
        entityManager.persist(virtualProduct);

        RealProduct realProduct = new RealProduct();
        realProduct.setName("Real");
        realProduct.setDescription("real description");
        realProduct.setCreated(LocalDateTime.now());
        realProduct.setWeight(2.7f);
        realProduct.setWidth(10);
        realProduct.setLength(15);
        realProduct.setHeight(25);
        entityManager.persist(realProduct);

        entityManager.flush();
        entityManager.clear();

        logger.info(entityManager.find(VirtualProduct.class, virtualProduct.getId()));
        logger.info(entityManager.find(RealProduct.class, realProduct.getId()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
