package com.example.hibernate.service;

import com.example.hibernate.entity.Customer;
import com.example.hibernate.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    /*private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer getCustomer(Long id) {
        return customerDao.getCustomer(id);
    }

    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }*/

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findByIdWithOrders(id);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
