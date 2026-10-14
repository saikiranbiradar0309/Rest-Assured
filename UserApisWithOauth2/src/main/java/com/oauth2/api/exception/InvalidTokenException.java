package com.oauth2.api.exception;

public class InvalidTokenException extends OAuth2Exception {

    public InvalidTokenException() {
        super("invalid_token", "Token is invalid or expired");
    }

}