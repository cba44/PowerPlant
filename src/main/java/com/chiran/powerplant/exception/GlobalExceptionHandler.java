package com.chiran.powerplant.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = BatteriesNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleBatteriesNotFound(BatteriesNotFoundException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = IllegalInputException.class)
	public ResponseEntity<ExceptionResponse> handleIllegalInput(IllegalInputException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}
