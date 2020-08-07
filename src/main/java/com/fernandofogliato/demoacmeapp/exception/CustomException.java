package com.fernandofogliato.demoacmeapp.exception;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomException {
	private LocalDateTime timestamp;
	private String message;
	private String details;
}
