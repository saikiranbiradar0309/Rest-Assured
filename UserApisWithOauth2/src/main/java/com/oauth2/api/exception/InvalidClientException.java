package com.oauth2.api.exception;

public class InvalidClientException extends RuntimeException {

	public InvalidClientException() {
		super();
	}

	public InvalidClientException(String message) {
		super(message);
	}

}