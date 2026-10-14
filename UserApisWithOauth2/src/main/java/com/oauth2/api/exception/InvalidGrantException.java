package com.oauth2.api.exception;

public class InvalidGrantException extends OAuth2Exception {

	public InvalidGrantException() {
		super("invalid_grant", "Invalid or expired grant");
	}

	public InvalidGrantException(String message) {
		super("invalid_grant", message);
	}

}