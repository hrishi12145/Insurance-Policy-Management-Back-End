package com.insurance.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	private LocalDateTime timeStamp;
	private String message;
	private List<String> fieldErrors = new ArrayList<>();

	public ErrorResponse() {
		super();

	}

	public ErrorResponse(LocalDateTime timeStamp, String message, List<String> fieldErrors) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.fieldErrors = fieldErrors;
	}

	public ErrorResponse(LocalDateTime timeStamp, String message) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getFieldErrors() {
		return fieldErrors;
	}
}
