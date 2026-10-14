package com.oauth2.api.resource.exception;

public class DuplicateDepartmentException extends RuntimeException {

    public DuplicateDepartmentException() {
        super("Department already exists.");
    }
}