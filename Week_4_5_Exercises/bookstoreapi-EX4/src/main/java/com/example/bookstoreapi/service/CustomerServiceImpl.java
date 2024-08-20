package com.example.bookstoreapi.service;

import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer registerCustomer(String name, String email, String password) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        return createCustomer(customer);
    }
}

