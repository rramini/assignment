package com.assignment.publisher.models.exceptions;

import org.springframework.validation.Errors;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = -5312390585320429158L;
	private final transient Errors errors;
	private final String message;

	public BadRequestException(String message, Errors errors) {
		super(message);
		this.errors = errors;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Errors getErrors() {
		return errors;
	}
}
