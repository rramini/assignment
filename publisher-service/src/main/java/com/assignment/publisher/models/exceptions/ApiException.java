package com.assignment.publisher.models.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1019187335413641177L;
	private  HttpStatus statusCode;
    
    public ApiException(Throwable cause) {
		super(cause);
	}
    
    public ApiException(HttpStatus statusCode, String statusMessage){
        super(statusMessage);
        this.statusCode = statusCode;
    }
    
    public HttpStatus getStatusCode() {
        return statusCode;
    }

}
