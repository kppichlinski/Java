package org.example.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Customer;
import org.example.entity.CustomerDetails;
import org.example.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class OrphantRemoval {

    private static final Logger logger = LogManager.getLogger(OrphantRemoval.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Order order = entityManager.find(Order.class, 2L);
        entityManager.remove(order);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
