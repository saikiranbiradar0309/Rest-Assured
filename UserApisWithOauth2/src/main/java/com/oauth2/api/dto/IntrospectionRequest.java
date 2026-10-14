package com.oauth2.api.dto;

import jakarta.validation.constraints.NotBlank;

public class IntrospectionRequest {

	@NotBlank
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}