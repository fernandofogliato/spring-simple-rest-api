package com.fernandofogliato.demoacmeapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id")
public class InvoiceDto {

	private long id;
	private InstallationDto installation;
	private String code;
	private LocalDateTime dueDate;
	private BigDecimal totalAmount;
}
