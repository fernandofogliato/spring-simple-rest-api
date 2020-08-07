package com.fernandofogliato.demoacmeapp.controller;

import com.fernandofogliato.demoacmeapp.domain.Customer;
import com.fernandofogliato.demoacmeapp.exception.ResourceNotFoundException;
import com.fernandofogliato.demoacmeapp.repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@Api(value = "Acme AP Customer Service")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @ApiOperation("Show all customers")
    @GetMapping
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @ApiOperation("Find customer by tax-identifier")
    @GetMapping("/{taxIdentifier}")
    public Optional<Customer> getByTaxIdentifier(@PathVariable String taxIdentifier) {
        Optional<Customer> cliente = customerRepository.findByTaxIdentifier(taxIdentifier);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return cliente;
    }

    @ApiOperation(value = "Create a new customer")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
