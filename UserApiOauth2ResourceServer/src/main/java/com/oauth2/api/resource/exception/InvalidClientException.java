package com.oauth2.api.resource.exception;

public class InvalidClientException extends RuntimeException {

	public InvalidClientException() {
		super("Client authentication failed.");
	}

	public InvalidClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidClientException(String message) {
		super(message);
	}

	public InvalidClientException(Throwable cause) {
		super(cause);
	}

}