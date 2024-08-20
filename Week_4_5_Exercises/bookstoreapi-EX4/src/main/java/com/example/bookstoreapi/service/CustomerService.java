package com.example.bookstoreapi.service;

import com.example.bookstoreapi.model.Customer;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Customer registerCustomer(String name, String email, String password);
}

