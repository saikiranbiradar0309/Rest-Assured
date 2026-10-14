package com.oauth2.api.exception;

public class DuplicatePermissionException extends RuntimeException {

    public DuplicatePermissionException(String message) {
        super(message);
    }

}