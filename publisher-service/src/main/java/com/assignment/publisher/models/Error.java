package com.assignment.publisher.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error
 */
public class Error {
	@JsonProperty("code")
	private String code = null;

	@JsonProperty("message")
	private String message = null;

	public Error(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
