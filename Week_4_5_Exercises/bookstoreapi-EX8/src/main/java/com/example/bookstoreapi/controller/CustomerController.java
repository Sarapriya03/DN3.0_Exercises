package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.mapper.CustomerMapper;
import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.service.CustomerService;
import com.example.bookstoreapi.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        CustomerDTO customerDTO = customerMapper.toDto(customer);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOs = customers.stream()
                                                  .map(customerMapper::toDto)
                                                  .collect(Collectors.toList());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        return new ResponseEntity<>(customerDTOs, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer createdCustomer = customerService.createCustomer(customer);
        CustomerDTO createdCustomerDTO = customerMapper.toDto(createdCustomer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/customers/" + createdCustomerDTO.getId());
        return new ResponseEntity<>(createdCustomerDTO, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer.setId(id);
        Customer updatedCustomer = customerService.updateCustomer(id, customer)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        CustomerDTO updatedCustomerDTO = customerMapper.toDto(updatedCustomer);
        return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
