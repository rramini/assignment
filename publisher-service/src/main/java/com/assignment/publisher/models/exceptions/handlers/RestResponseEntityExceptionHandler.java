package com.assignment.publisher.models.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	private final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleAllExceptions(RuntimeException ex, WebRequest request) {

		log.error("Exception occurred: ", ex);
		return handleExceptionInternal(ex,
				createErrorResp(HttpStatus.INTERNAL_SERVER_ERROR, "Exception in processing data."), new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(value = ResponseStatusException.class)
	protected ResponseEntity<Object> handleKnownErrors(ResponseStatusException ex, WebRequest request) {
		log.error("Exception occurred: ", ex);
		return handleExceptionInternal(ex, createErrorResp(ex.getStatus(), ex.getMessage()).toString(),
				new HttpHeaders(), ex.getStatus(), request);
	}

	private com.assignment.publisher.models.Error createErrorResp(HttpStatus status, String message) {
		com.assignment.publisher.models.Error error = new com.assignment.publisher.models.Error();
		error.setCode(status.toString());
		error.setMessage(message);
		return error;
	}
}
