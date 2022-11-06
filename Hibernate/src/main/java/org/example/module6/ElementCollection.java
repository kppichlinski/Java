package org.example.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.types.AddressType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ElementCollection {

    private static final Logger logger = LogManager.getLogger(ElementCollection.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Customer customer = Customer.builder()
                .firstname("Customer 1")
                .lastname("Customer 1")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .address(Arrays.asList(
                        Address.builder()
                                .addressType(AddressType.BILLING)
                                .street("Majowa 2/5")
                                .postalCode("23-342")
                                .city("WWA")
                                .build(),
                        Address.builder()
                                .addressType(AddressType.BILLING)
                                .street("Brzozowa 5/5")
                                .postalCode("23-345")
                                .city("ZS")
                                .build(),
                        Address.builder()
                                .addressType(AddressType.BILLING)
                                .street("Polna 5/2")
                                .postalCode("21-342")
                                .city("GD")
                                .build()
                ))
                .build();

        entityManager.persist(customer);

        entityManager.flush();
        entityManager.clear();

        Customer foundCustomer = entityManager.find(Customer.class, customer.getId());
        logger.info(foundCustomer);
        logger.info(foundCustomer.getAddress());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
