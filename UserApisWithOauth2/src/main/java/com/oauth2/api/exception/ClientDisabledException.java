package com.oauth2.api.exception;

public class ClientDisabledException extends RuntimeException {

	public ClientDisabledException() {
		super();
	}

	public ClientDisabledException(String message) {
		super(message);
	}

}