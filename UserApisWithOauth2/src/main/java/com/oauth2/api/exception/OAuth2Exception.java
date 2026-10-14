package com.oauth2.api.exception;

public class OAuth2Exception extends RuntimeException {

    private final String error;

    public OAuth2Exception(String error, String message) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}