package com.oauth2.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.api.dto.TokenIntrospectionResponse;
import com.oauth2.api.service.TokenIntrospectionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/oauth2")
public class TokenIntrospectionController {

	private final TokenIntrospectionService tokenIntrospectionService;

	public TokenIntrospectionController(TokenIntrospectionService tokenIntrospectionService) {

		this.tokenIntrospectionService = tokenIntrospectionService;
	}

	@Operation(
	        summary="Token Introspection")
	@PostMapping(value = "/introspect", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<TokenIntrospectionResponse> introspect(

			@RequestParam("token") String token) {

		return ResponseEntity.ok(tokenIntrospectionService.introspect(token));
	}

}