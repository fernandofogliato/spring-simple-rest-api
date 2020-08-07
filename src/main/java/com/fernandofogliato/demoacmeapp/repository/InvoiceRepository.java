package com.fernandofogliato.demoacmeapp.repository;

import java.util.List;
import java.util.Optional;

import com.fernandofogliato.demoacmeapp.domain.Invoice;
import com.fernandofogliato.demoacmeapp.domain.Installation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	Optional<Invoice> findByCode(String code);

	List<Invoice> findByInstallation(Installation installation);
}
