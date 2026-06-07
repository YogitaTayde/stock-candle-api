package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class CandleServiceException extends RuntimeException {

	private String errorMessage;
	private HttpStatus httpStatus;

	public CandleServiceException(String errorMessage, HttpStatus httpStatus) {
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
