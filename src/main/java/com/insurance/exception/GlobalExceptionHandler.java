package com.insurance.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), null);
		return handleExceptionInternal(ex, errorResponse, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldError = result.getFieldErrors();
		List<String> errors = new ArrayList<>();
		for (FieldError error : fieldError) {
			errors.add(error.getDefaultMessage());
		}

		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), "Bad Request", errors);

		return handleExceptionInternal(ex, errorResponse, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>("Please Change Http Method Type Request", HttpStatus.NOT_FOUND);
	}

}