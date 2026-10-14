package com.oauth2.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.api.service.TokenRevocationService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/oauth2")
public class TokenRevocationController {

	private final TokenRevocationService tokenRevocationService;

	public TokenRevocationController(TokenRevocationService tokenRevocationService) {

		this.tokenRevocationService = tokenRevocationService;
	}

	@Operation(
	        summary="Token Revocation")
	@PostMapping(value = "/revoke", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Void> revoke(

			@RequestParam("token") String token) {

		tokenRevocationService.revoke(token);

		return ResponseEntity.ok().build();
	}
}