package com.oauth2.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oauth2.api.dto.TokenResponse;
import com.oauth2.api.entity.AccessToken;
import com.oauth2.api.entity.OAuthClient;
import com.oauth2.api.entity.Permission;
import com.oauth2.api.entity.RefreshToken;
import com.oauth2.api.entity.Role;
import com.oauth2.api.entity.User;
import com.oauth2.api.repository.AccessTokenRepository;
import com.oauth2.api.repository.RefreshTokenRepository;
import com.oauth2.api.repository.UserRepository;
import com.oauth2.api.util.JwtUtil;
import com.oauth2.api.util.TokenGenerator;

@Service
public class TokenService {

	private final JwtUtil jwtUtil;

	private final TokenGenerator generator;

	private final AccessTokenRepository accessRepository;

	private final RefreshTokenRepository refreshRepository;

	private final UserRepository userRepository;

	public TokenService(JwtUtil jwtUtil, TokenGenerator generator, AccessTokenRepository accessRepository,
			RefreshTokenRepository refreshRepository, UserRepository userRepository) {

		this.jwtUtil = jwtUtil;
		this.generator = generator;
		this.accessRepository = accessRepository;
		this.refreshRepository = refreshRepository;

		this.userRepository = userRepository;
	}

	@Transactional
	public TokenResponse createToken(OAuthClient client, String username, String scope) {

		User user = userRepository.findByUsername(username).orElseThrow();

		List<String> roles = user.getRoles().stream().map(Role::getRoleName).toList();

		List<String> permissions = user.getRoles().stream().flatMap(role -> role.getPermissions().stream())
				.map(Permission::getPermissionName).distinct().toList();

		String accessToken = jwtUtil.generateAccessToken(username, client.getClientId(), scope, roles, permissions);

		String refreshToken = generator.generateRefreshToken();

		LocalDateTime issued = LocalDateTime.now();

		LocalDateTime accessExpiry = issued.plusSeconds(client.getAccessTokenValidity());

		LocalDateTime refreshExpiry = issued.plusSeconds(client.getRefreshTokenValidity());

		AccessToken token = new AccessToken();

		token.setAccessToken(accessToken);

		token.setClientId(client.getClientId());

		token.setUsername(username);

		token.setScope(scope);

		token.setIssuedAt(issued);

		token.setExpiresAt(accessExpiry);

		accessRepository.save(token);

		RefreshToken refresh = new RefreshToken();

		refresh.setRefreshToken(refreshToken);

		refresh.setClient(client);

		refresh.setIssuedAt(issued);

		refresh.setUsername(username);

		refresh.setExpiresAt(refreshExpiry);

		refreshRepository.save(refresh);

		TokenResponse tokenResponse = new TokenResponse();

		tokenResponse.setAccessToken(accessToken);

		tokenResponse.setRefreshToken(refreshToken);

		tokenResponse.setTokenType("Bearer");

		tokenResponse.setExpiresIn((long) client.getRefreshTokenValidity());

		tokenResponse.setScope(scope);

		return tokenResponse;

	}

	@Transactional
	public TokenResponse createClientCredentialsToken(OAuthClient client, String scope) {

		String accessToken = jwtUtil.generateClientCredentialsToken(client.getClientId(), scope);

		LocalDateTime issued = LocalDateTime.now();

		LocalDateTime expiry = issued.plusSeconds(client.getAccessTokenValidity());

		AccessToken token = new AccessToken();

		token.setAccessToken(accessToken);

		token.setClientId(client.getClientId());

		token.setUsername(client.getClientId());

		token.setScope(client.getScopes());

		token.setIssuedAt(issued);

		token.setExpiresAt(expiry);

		accessRepository.save(token);

		TokenResponse response = new TokenResponse();

		response.setAccessToken(accessToken);

		response.setTokenType("Bearer");

		response.setExpiresIn((long) client.getAccessTokenValidity());

		response.setScope(scope);

		return response;
	}

}