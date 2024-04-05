package com.example.practice.msscbrewery.service.impl;

import com.example.practice.msscbrewery.service.CustomerService;
import com.example.practice.msscbrewery.web.model.CustomerDTO;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDTO getCustomerById(UUID customerId) {
        return CustomerDTO.builder()
            .id(customerId)
            .name("Jim Halpert")
            .build();
    }


    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder()
            .id(UUID.randomUUID())
            .name(customerDTO.getName())
            .build();
    }


    @Override
    public CustomerDTO updateCustomer(UUID customerId, CustomerDTO customerDTO) {
        return CustomerDTO.builder()
            .id(customerId)
            .name(customerDTO.getName())
            .build();
    }


    @Override
    public void deleteCustomer(UUID uuid) {

    }
}
