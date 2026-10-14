package com.oauth2.api.exception;

public class ClientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException() {
        super("OAuth client not found.");
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}