package com.oauth2.api.exception;

public class DuplicateUsernameException extends RuntimeException {

	public DuplicateUsernameException() {

		super("Username already exists");
	}

	public DuplicateUsernameException(String message) {

		super(message);
	}
}