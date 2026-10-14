package com.oauth2.api.exception;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() {
        super("Access token has expired.");
    }
}