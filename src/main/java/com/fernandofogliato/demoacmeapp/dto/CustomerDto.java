package com.fernandofogliato.demoacmeapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
public class CustomerDto {

	private long id;
	private AddressDto address;
	private String name;
	private String taxIdentifier;
	private LocalDate birthDate;
}
