package org.example.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.CustomerDetails;
import org.example.types.AddressType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class IdsMapping {

    private static final Logger logger = LogManager.getLogger(IdsMapping.class);

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Customer customer = entityManager.find(Customer.class, 1L);
        CustomerDetails customerDetails = CustomerDetails.builder()
                .birthPlace("Warszawa")
                .birthDay(LocalDate.of(2000, 1, 21))
                .fatherName("Jan")
                .motherName("Barbara")
                .pesel("11111111111")
                .customer(customer)
                .build();

        customer.setCustomerDetails(customerDetails);

        entityManager.persist(customer);

        logger.info(customer);
        logger.info(customer.getCustomerDetails());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
