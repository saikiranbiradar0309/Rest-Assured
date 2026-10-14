package com.oauth2.api.service;

import org.springframework.stereotype.Service;

import com.oauth2.api.dto.TokenResponse;
import com.oauth2.api.entity.OAuthClient;

@Service
public class ClientCredentialsService {

	private final ClientService clientService;
	private final TokenService tokenService;

	public ClientCredentialsService(ClientService clientService, TokenService tokenService) {

		this.clientService = clientService;
		this.tokenService = tokenService;
	}

	/**
	 * Generate Access Token using Client Credentials Grant
	 */
	public TokenResponse generateToken(String clientId, String clientSecret, String scope) {

		// Validate OAuth Client
		OAuthClient client = clientService.validateClient(clientId, clientSecret);

		// If scope is not provided, use client's default scope
		if (scope == null || scope.isBlank()) {
			scope = client.getScopes();
		}

		// Generate Access Token
		return tokenService.createClientCredentialsToken(client, scope);
	}
}