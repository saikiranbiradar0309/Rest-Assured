package com.oauth2.api.resource.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Map<String, Object> body = new LinkedHashMap<>();

		body.put("timestamp", LocalDateTime.now());
		body.put("status", 401);
		body.put("error", "Unauthorized");
		body.put("message", "Invalid or Expired JWT Token");
		body.put("path", request.getRequestURI());

		ObjectMapper mapper = new ObjectMapper();

		mapper.writeValue(response.getOutputStream(), body);
	}
}