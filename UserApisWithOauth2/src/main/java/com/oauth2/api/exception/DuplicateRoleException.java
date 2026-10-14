package com.oauth2.api.exception;

public class DuplicateRoleException extends RuntimeException {

	public DuplicateRoleException() {

		super("Role already exists");
	}

	public DuplicateRoleException(String message) {

		super(message);
	}
}