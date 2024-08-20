package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Endpoint to create a new customer by accepting a JSON request body
    @PostMapping("/json")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    // Endpoint to process form data for customer registrations
    @PostMapping("/form")
    public ResponseEntity<Customer> registerCustomer(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        Customer registeredCustomer = customerService.registerCustomer(name, email, password);
        return ResponseEntity.ok(registeredCustomer);
    }
}
