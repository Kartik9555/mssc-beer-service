package com.example.practice.msscbrewery.service;

import com.example.practice.msscbrewery.web.model.CustomerDTO;
import java.util.UUID;

public interface CustomerService {

    CustomerDTO getCustomerById(UUID customerId);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(UUID customerId, CustomerDTO customerDTO);

    void deleteCustomer(UUID uuid);
}
