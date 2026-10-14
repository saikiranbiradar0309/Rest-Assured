package com.oauth2.api.exception;

public class RoleNotFoundException extends RuntimeException {

	public RoleNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RoleNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RoleNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public RoleNotFoundException(String string) {
		super("Role not found");
	}
}