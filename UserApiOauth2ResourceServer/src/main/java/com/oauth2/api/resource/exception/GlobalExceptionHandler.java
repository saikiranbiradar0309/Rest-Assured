package com.oauth2.api.resource.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/*---------------------------------------------------
	 Employee Not Found - 404
	---------------------------------------------------*/

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ApiError> employeeNotFound(EmployeeNotFoundException ex, HttpServletRequest request) {

		return build(HttpStatus.NOT_FOUND, "employee_not_found", ex.getMessage(), request.getRequestURI());
	}

	/*---------------------------------------------------
	 Duplicate Email - 409
	---------------------------------------------------*/

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ApiError> duplicateEmail(DuplicateEmailException ex, HttpServletRequest request) {

		return build(HttpStatus.CONFLICT, "duplicate_email", ex.getMessage(), request.getRequestURI());
	}

	/*---------------------------------------------------
	 Duplicate Department - 409
	---------------------------------------------------*/

	@ExceptionHandler(DuplicateDepartmentException.class)
	public ResponseEntity<ApiError> duplicateDepartment(DuplicateDepartmentException ex, HttpServletRequest request) {

		return build(HttpStatus.CONFLICT, "duplicate_department", ex.getMessage(), request.getRequestURI());
	}

	/*---------------------------------------------------
	 Validation Error - 400
	---------------------------------------------------*/

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex, HttpServletRequest request) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {

			String field = ((FieldError) error).getField();

			errors.put(field, error.getDefaultMessage());
		});

		return build(HttpStatus.BAD_REQUEST, "validation_failed", errors.toString(), request.getRequestURI());
	}

	/*---------------------------------------------------
	 Invalid JWT Token - 401
	---------------------------------------------------*/

	@ExceptionHandler(InvalidJwtException.class)
	public ResponseEntity<ApiError> invalidJwt(InvalidJwtException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "invalid_token", ex.getMessage(), request.getRequestURI());
	}

	/*---------------------------------------------------
	 Bad Credentials - 401
	---------------------------------------------------*/

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiError> badCredentials(BadCredentialsException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "unauthorized", ex.getMessage(), request.getRequestURI());
	}

	/*---------------------------------------------------
	 Access Denied - 403
	---------------------------------------------------*/

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiError> accessDenied(AccessDeniedException ex, HttpServletRequest request) {

		return build(HttpStatus.FORBIDDEN, "access_denied", "You don't have permission", request.getRequestURI());
	}

	/*---------------------------------------------------
	 Illegal Argument - 400
	---------------------------------------------------*/

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiError> illegalArgument(IllegalArgumentException ex, HttpServletRequest request) {

		return build(HttpStatus.BAD_REQUEST, "bad_request", ex.getMessage(), request.getRequestURI());
	}

	/*---------------------------------------------------
	 Global Exception - 500
	---------------------------------------------------*/

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> exception(Exception ex, HttpServletRequest request) {

		ex.printStackTrace();

		return build(HttpStatus.INTERNAL_SERVER_ERROR, "server_error", ex.getMessage(), request.getRequestURI());
	}

	/*---------------------------------------------------
	 Common Response Builder
	---------------------------------------------------*/

	private ResponseEntity<ApiError> build(HttpStatus status, String error, String message, String path) {

		ApiError apiError = new ApiError(status.value(), error, message, path);

		return ResponseEntity.status(status).body(apiError);
	}

}