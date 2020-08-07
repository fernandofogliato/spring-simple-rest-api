package com.fernandofogliato.demoacmeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "installation_id")
	private Installation installation;

	private String code;
	private LocalDateTime dueDate;
	private BigDecimal totalAmount;
}
