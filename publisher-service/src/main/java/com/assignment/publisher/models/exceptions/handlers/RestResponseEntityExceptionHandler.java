package com.assignment.publisher.models.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.assignment.publisher.models.Error;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<Error> handleGlobalExceptions(MethodArgumentNotValidException ex, WebRequest request) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(createErrorResp(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<Error> handleHeadersIssue() {
		return new ResponseEntity<>(createErrorResp(HttpStatus.BAD_REQUEST, "Required headers are missing."),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Error> unhandledPath(final NoHandlerFoundException e) {

		return new ResponseEntity<>(createErrorResp(HttpStatus.NOT_FOUND, "No handler found"), HttpStatus.NOT_FOUND);
	}

	private com.assignment.publisher.models.Error createErrorResp(HttpStatus status, String message) {
		return new Error(status.name(), message);
	}
}
