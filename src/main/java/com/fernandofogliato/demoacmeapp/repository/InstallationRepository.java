package com.fernandofogliato.demoacmeapp.repository;

import com.fernandofogliato.demoacmeapp.domain.Customer;
import com.fernandofogliato.demoacmeapp.domain.Installation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstallationRepository extends JpaRepository<Installation, Long> {

	Optional<Installation> findByCode(String code);

	List<Installation> findByCustomer(Customer customer);
}
