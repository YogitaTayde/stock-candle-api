package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception exception) {

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CandleServiceException.class)
	public ResponseEntity<String> handleCandleServiceException(CandleServiceException exception) {

		return new ResponseEntity<>(exception.getMessage(), exception.getHttpStatus());
	}
}