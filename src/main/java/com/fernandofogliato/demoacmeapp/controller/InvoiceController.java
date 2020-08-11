package com.fernandofogliato.demoacmeapp.controller;

import com.fernandofogliato.demoacmeapp.domain.Invoice;
import com.fernandofogliato.demoacmeapp.dto.InvoiceDto;
import com.fernandofogliato.demoacmeapp.exception.ResourceNotFoundException;
import com.fernandofogliato.demoacmeapp.repository.InvoiceRepository;
import com.fernandofogliato.demoacmeapp.service.InvoiceMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoices")
@Api(value = "Acme AP Invoice Service", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceController {

	private final InvoiceRepository invoiceRepository;
	private final InvoiceMapper mapper;

	public InvoiceController(InvoiceRepository invoiceRepository,
							 InvoiceMapper mapper) {
		this.invoiceRepository = invoiceRepository;
		this.mapper = mapper;
	}

	@ApiOperation(value = "Show all invoices")
	public List<InvoiceDto> getAll() {
		return invoiceRepository.findAll().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Find an invoice by code")
	@GetMapping("/{code}")
	public InvoiceDto getByCode(@PathVariable String code) {
		Optional<Invoice> invoice = invoiceRepository.findByCode(code);
		if (invoice.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return mapper.toDto(invoice.get());
	}

	@ApiOperation(value = "Create a new invoice")
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody InvoiceDto invoice) {
		Invoice createdInvoice = invoiceRepository.save(mapper.toEntity(invoice));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdInvoice.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
