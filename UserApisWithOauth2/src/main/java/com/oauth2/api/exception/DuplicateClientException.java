package com.oauth2.api.exception;

public class DuplicateClientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateClientException() {
        super("OAuth client already exists.");
    }

    public DuplicateClientException(String message) {
        super(message);
    }
}