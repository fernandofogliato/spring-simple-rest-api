package com.fernandofogliato.demoacmeapp.controller;

import com.fernandofogliato.demoacmeapp.domain.Invoice;
import com.fernandofogliato.demoacmeapp.exception.ResourceNotFoundException;
import com.fernandofogliato.demoacmeapp.repository.InvoiceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
@Api(value = "Acme AP Invoice Service", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceController {

	private final InvoiceRepository invoiceRepository;

	public InvoiceController(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@ApiOperation(value = "Show all invoices")
	public List<Invoice> getAll() {
		return invoiceRepository.findAll();
	}

	@ApiOperation(value = "Find an invoice by code")
	@GetMapping("/{code}")
	public Optional<Invoice> getByCode(@PathVariable String code) {
		Optional<Invoice> invoice = invoiceRepository.findByCode(code);
		if (invoice.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return invoice;
	}

	@ApiOperation(value = "Create a new invoice")
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Invoice invoice) {
		Invoice createdInvoice = invoiceRepository.save(invoice);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdInvoice.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
