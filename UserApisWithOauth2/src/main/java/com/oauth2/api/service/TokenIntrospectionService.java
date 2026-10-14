package com.oauth2.api.service;

import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.oauth2.api.dto.TokenIntrospectionResponse;
import com.oauth2.api.entity.AccessToken;
import com.oauth2.api.repository.AccessTokenRepository;

@Service
public class TokenIntrospectionService {

	private final AccessTokenRepository accessTokenRepository;

	public TokenIntrospectionService(AccessTokenRepository accessTokenRepository) {

		this.accessTokenRepository = accessTokenRepository;
	}

	public TokenIntrospectionResponse introspect(String token) {

		AccessToken accessToken = accessTokenRepository.findByAccessToken(token).orElse(null);

		// Token not found
		if (accessToken == null) {

			TokenIntrospectionResponse response = new TokenIntrospectionResponse();

			response.setActive(false);

			return response;

		}

		// Token expired
		if (accessToken.getExpiresAt().isBefore(java.time.LocalDateTime.now())) {

			TokenIntrospectionResponse response = new TokenIntrospectionResponse();

			response.setActive(false);

			return response;
		}

		TokenIntrospectionResponse response = new TokenIntrospectionResponse();
		response.setActive(true);
		response.setUsername(accessToken.getUsername());
		response.setClientId(accessToken.getClientId());
		response.setScope(accessToken.getScope());
		response.setExp(accessToken.getExpiresAt().toEpochSecond(ZoneOffset.UTC));

		// Active token
		return response;
	}
}