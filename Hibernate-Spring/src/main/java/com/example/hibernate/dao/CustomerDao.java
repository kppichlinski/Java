package com.example.hibernate.dao;

import com.example.hibernate.entity.Customer;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Customer getCustomer(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Transactional
    public List<Customer> getCustomers() {
        return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
    }
}
