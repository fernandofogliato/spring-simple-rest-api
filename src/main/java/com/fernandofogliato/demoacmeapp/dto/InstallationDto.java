package com.fernandofogliato.demoacmeapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class InstallationDto {

	private long id;
	private CustomerDto customer;
	private AddressDto address;
	private List<InvoiceDto> invoices;
	private String code;
	private LocalDateTime dateInstallation;
}
