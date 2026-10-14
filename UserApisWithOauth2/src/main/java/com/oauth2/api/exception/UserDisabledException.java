package com.oauth2.api.exception;

public class UserDisabledException extends RuntimeException {

    public UserDisabledException() {
        super("User account is disabled.");
    }
}