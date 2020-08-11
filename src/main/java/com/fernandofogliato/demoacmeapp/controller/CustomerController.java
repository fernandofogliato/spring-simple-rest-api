package com.fernandofogliato.demoacmeapp.controller;

import com.fernandofogliato.demoacmeapp.domain.Customer;
import com.fernandofogliato.demoacmeapp.dto.CustomerDto;
import com.fernandofogliato.demoacmeapp.exception.ResourceNotFoundException;
import com.fernandofogliato.demoacmeapp.repository.CustomerRepository;
import com.fernandofogliato.demoacmeapp.service.CustomerMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@Api(value = "Acme AP Customer Service")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerController(CustomerMapper customerMapper,
                              CustomerRepository customerRepository) {
        this.mapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @ApiOperation("Show all customers")
    @GetMapping
    public List<CustomerDto> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @ApiOperation("Find customer by tax-identifier")
    @GetMapping("/{taxIdentifier}")
    public CustomerDto getByTaxIdentifier(@PathVariable String taxIdentifier) {
        Optional<Customer> customer = customerRepository.findByTaxIdentifier(taxIdentifier);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return mapper.toDto(customer.get());
    }

    @ApiOperation(value = "Create a new customer")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CustomerDto customer) {
        Customer createdCustomer = customerRepository.save(mapper.toEntity(customer));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
