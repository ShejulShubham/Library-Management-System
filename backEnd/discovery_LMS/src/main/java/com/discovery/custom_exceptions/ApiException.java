package com.discovery.custom_exceptions;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.discovery.dto.ApiResponse;

public class ApiException extends RuntimeException {
	public ApiException(String message) {
		super(message);
	}



@ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<ApiResponse> handleConstraintViolation(ConstraintViolationException ex) {
    String message = "Constraint violation: " + ex.getMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message, "error"));
}
}