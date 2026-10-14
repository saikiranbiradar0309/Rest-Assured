package com.oauth2.api.resource.exception;

public class InvalidGrantException extends RuntimeException {

    public InvalidGrantException() {
        super("Invalid grant.");
    }
}