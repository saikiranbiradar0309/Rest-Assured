package com.oauth2.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.api.dto.TokenResponse;
import com.oauth2.api.entity.OAuthClient;
import com.oauth2.api.exception.UnsupportedGrantTypeException;
import com.oauth2.api.service.AuthenticationService;
import com.oauth2.api.service.ClientCredentialsService;
import com.oauth2.api.service.ClientService;
import com.oauth2.api.service.RefreshTokenService;
import com.oauth2.api.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/oauth2")
@Tag(name = "OAuth2 Token APIs")

public class TokenController {

	private final ClientService clientService;
	private final AuthenticationService authenticationService;
	private final TokenService tokenService;
	private final RefreshTokenService refreshTokenService;
	private final ClientCredentialsService clientCredentialsService;

	public TokenController(ClientService clientService, AuthenticationService authenticationService,
			TokenService tokenService, RefreshTokenService refreshTokenService,
			ClientCredentialsService clientCredentialsService) {

		this.clientService = clientService;
		this.authenticationService = authenticationService;
		this.tokenService = tokenService;
		this.refreshTokenService = refreshTokenService;
		this.clientCredentialsService = clientCredentialsService;
	}

	@Operation(summary = "Generate Access Token", description = "Password Grant")
	@PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<TokenResponse> token(

			@RequestParam("grant_type") String grantType,

			@RequestParam("client_id") String clientId,

			@RequestParam("client_secret") String clientSecret,

			@RequestParam(required = false) String username,

			@RequestParam(required = false) String password,

			@RequestParam(name = "refresh_token", required = false) String refreshToken,

			@RequestParam(required = false) String scope) {

		System.out.println("===== TOKEN REQUEST =====");
		System.out.println("Grant Type : " + grantType);
		System.out.println("Client Id  : " + clientId);
		System.out.println("Username   : " + username);
		System.out.println("Scope      : " + scope);

		switch (grantType) {

		case "password":

			OAuthClient client = clientService.validateClient(clientId, clientSecret);

			authenticationService.authenticate(username, password);

			return ResponseEntity.ok(tokenService.createToken(client, username, scope));

		case "client_credentials":

			OAuthClient machineClient = clientService.validateClient(clientId, clientSecret);

			return ResponseEntity.ok(tokenService.createClientCredentialsToken(machineClient, scope));

		case "refresh_token":

			return ResponseEntity.ok(refreshTokenService.refresh(refreshToken));

		default:

			throw new UnsupportedGrantTypeException();
		}
	}
}