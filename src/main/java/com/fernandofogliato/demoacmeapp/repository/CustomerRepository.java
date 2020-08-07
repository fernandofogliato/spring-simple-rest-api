package com.fernandofogliato.demoacmeapp.repository;

import java.util.Optional;

import com.fernandofogliato.demoacmeapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByTaxIdentifier(String taxIdentifier);
}
