package com.fernandofogliato.demoacmeapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	@org.springframework.web.bind.annotation.ExceptionHandler(java.lang.Exception.class)
	public final ResponseEntity<Object> handleAllException (java.lang.Exception ex, WebRequest request){
		CustomException customException = CustomException.builder()
				.timestamp(LocalDateTime.now())
				.message(ex.getMessage())
				.details(request.getDescription(false))
				.build();
		return new ResponseEntity(customException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
