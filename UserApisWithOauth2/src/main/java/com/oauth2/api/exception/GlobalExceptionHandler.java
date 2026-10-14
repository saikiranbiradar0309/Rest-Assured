package com.oauth2.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.oauth2.api.dto.OAuth2ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(OAuth2Exception.class)
	public ResponseEntity<OAuth2ErrorResponse> handleOAuthException(OAuth2Exception ex) {

		OAuth2ErrorResponse response = new OAuth2ErrorResponse();

		response.setError(ex.getError());

		response.setErrorDescription(ex.getMessage());

		response.setTimestamp(LocalDateTime.now().toString());

		HttpStatus status;

		switch (ex.getError()) {

		case "invalid_client":
			status = HttpStatus.UNAUTHORIZED;
			break;

		case "invalid_grant":
			status = HttpStatus.BAD_REQUEST;
			break;

		case "invalid_token":
			status = HttpStatus.UNAUTHORIZED;
			break;

		default:
			status = HttpStatus.BAD_REQUEST;
		}

		return ResponseEntity.status(status).body(response);
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<OAuth2ErrorResponse> handleException(Exception ex) {
//
//		OAuth2ErrorResponse response = OAuth2ErrorResponse.builder().error("server_error")
//				.errorDescription(ex.getMessage()).timestamp(LocalDateTime.now().toString()).build();
//
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//	}
//	

	@ExceptionHandler(InvalidClientException.class)
	public ResponseEntity<ApiError> invalidClient(InvalidClientException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "invalid_client", ex.getMessage(), request);
	}

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<ApiError> handleClientNotFound(ClientNotFoundException ex) {

		ApiError error = new ApiError();

		error.setStatus(HttpStatus.NOT_FOUND.value());

		error.setError("Not Found");

		error.setMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

//	@ExceptionHandler(DuplicateClientException.class)
//	public ResponseEntity<ApiError> handleDuplicateClient(DuplicateClientException ex) {
//
//		ApiError error = ApiError.builder().status(HttpStatus.CONFLICT.value()).error("Conflict")
//				.message(ex.getMessage()).build();
//
//		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
//	}

	@ExceptionHandler(InvalidGrantException.class)
	public ResponseEntity<ApiError> invalidGrant(InvalidGrantException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "invalid_grant", ex.getMessage(), request);
	}

	@ExceptionHandler(InvalidRefreshTokenException.class)
	public ResponseEntity<ApiError> invalidRefreshToken(InvalidRefreshTokenException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "invalid_refresh_token", ex.getMessage(), request);
	}

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ApiError> invalidToken(InvalidTokenException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "invalid_token", ex.getMessage(), request);
	}

	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<ApiError> tokenExpired(TokenExpiredException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "token_expired", ex.getMessage(), request);
	}

	@ExceptionHandler(UnsupportedGrantTypeException.class)
	public ResponseEntity<ApiError> unsupportedGrant(UnsupportedGrantTypeException ex, HttpServletRequest request) {

		return build(HttpStatus.BAD_REQUEST, "unsupported_grant_type", ex.getMessage(), request);
	}

	@ExceptionHandler(ClientDisabledException.class)
	public ResponseEntity<ApiError> clientDisabled(ClientDisabledException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "client_disabled", ex.getMessage(), request);
	}

	@ExceptionHandler(UserDisabledException.class)
	public ResponseEntity<ApiError> userDisabled(UserDisabledException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "user_disabled", ex.getMessage(), request);
	}

	@ExceptionHandler(DuplicateClientException.class)
	public ResponseEntity<ApiError> duplicateClient(DuplicateClientException ex, HttpServletRequest request) {

		return build(HttpStatus.CONFLICT, "duplicate_client", ex.getMessage(), request);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

		return build(HttpStatus.NOT_FOUND, "resource_not_found", ex.getMessage(), request);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiError> badCredentials(BadCredentialsException ex, HttpServletRequest request) {

		return build(HttpStatus.UNAUTHORIZED, "invalid_grant", "Invalid username or password.", request);
	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Map<String, String>> validation(MethodArgumentNotValidException ex) {
//
//		Map<String, String> errors = new HashMap<>();
//
//		ex.getBindingResult().getAllErrors().forEach(error -> {
//
//			String field = ((FieldError) error).getField();
//
//			errors.put(field, error.getDefaultMessage());
//
//		});
//
//		return ResponseEntity.badRequest().body(errors);
//	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ApiError> exception(Exception ex, HttpServletRequest request) {
//
//		ex.printStackTrace();
//
//		return build(HttpStatus.INTERNAL_SERVER_ERROR, "server_error", ex.getMessage(), request);
//	}

//	@ExceptionHandler(ClientNotFoundException.class)
//	public ResponseEntity<ApiError> handleClientNotFound(ClientNotFoundException ex, HttpServletRequest request) {
//
//		ApiError error = ApiError.builder().status(HttpStatus.NOT_FOUND.value())
//				.error(HttpStatus.NOT_FOUND.getReasonPhrase()).message(ex.getMessage()).path(request.getRequestURI())
//				.build();
//
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//	}

//	@ExceptionHandler(DuplicateClientException.class)
//	public ResponseEntity<ApiError> handleDuplicateClient(DuplicateClientException ex, HttpServletRequest request) {
//
//		ApiError error = ApiError.builder().status(HttpStatus.CONFLICT.value())
//				.error(HttpStatus.CONFLICT.getReasonPhrase()).message(ex.getMessage()).path(request.getRequestURI())
//				.build();
//
//		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleException(Exception ex, HttpServletRequest request) {

		ApiError error = new ApiError();

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

		error.setMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	@ExceptionHandler(DuplicateRoleException.class)
	public ResponseEntity<ApiError> duplicateRole(DuplicateRoleException ex, HttpServletRequest request) {

		ApiError error = new ApiError();

		error.setStatus(HttpStatus.CONFLICT.value());

		error.setError("duplicate_role");

		error.setMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(DuplicateUsernameException.class)
	public ResponseEntity<ApiError> duplicateUsername(DuplicateUsernameException ex, HttpServletRequest request) {

		ApiError error = new ApiError();

		error.setStatus(HttpStatus.CONFLICT.value());

		error.setError("duplicate_role");

		error.setMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ApiError> duplicateEmail(DuplicateEmailException ex, HttpServletRequest request) {

		ApiError error = new ApiError();

		error.setStatus(HttpStatus.CONFLICT.value());

		error.setError("duplicate_role");

		error.setMessage(ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {

		String message = ex.getBindingResult().getFieldError().getDefaultMessage();

		ApiError error = new ApiError();

		error.setStatus(HttpStatus.BAD_REQUEST.value());

		error.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

		error.setMessage(ex.getMessage());

		return ResponseEntity.badRequest().body(error);
	}

	private ResponseEntity<ApiError> build(HttpStatus status, String error, String message,
			HttpServletRequest request) {

		ApiError apiError = new ApiError();

		apiError.setStatus(status.value());

		apiError.setError(error);

		apiError.setMessage(message);

		return new ResponseEntity<>(apiError, status);
	}
}