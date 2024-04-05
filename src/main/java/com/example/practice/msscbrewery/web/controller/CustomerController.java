package com.example.practice.msscbrewery.web.controller;

import com.example.practice.msscbrewery.service.CustomerService;
import com.example.practice.msscbrewery.web.model.CustomerDTO;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok(customerService.getCustomerById(UUID.fromString(customerId)));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCustomer(@Validated @RequestBody CustomerDTO customerDTO){
        CustomerDTO savedCustomer = customerService.createCustomer(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/customer/" + savedCustomer.getId());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

    @PutMapping("/{cusotmerId}")
    public ResponseEntity<HttpStatus> updateCustomer(@PathVariable("cusotmerId") String customerId, @Validated @RequestBody CustomerDTO customerDTO){
        CustomerDTO updatedCustomer = customerService.updateCustomer(UUID.fromString(customerId), customerDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") String customerId){
        customerService.deleteCustomer(UUID.fromString(customerId));
    }
}
