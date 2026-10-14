package com.oauth2.api.resource.exception;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException() {
        super("JWT token is invalid.");
    }
}