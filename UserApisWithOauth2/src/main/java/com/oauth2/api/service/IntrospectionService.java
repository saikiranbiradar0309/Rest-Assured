package com.oauth2.api.service;

import org.springframework.stereotype.Service;

import com.oauth2.api.dto.IntrospectionResponse;
import com.oauth2.api.repository.AccessTokenRepository;
import com.oauth2.api.util.JwtUtil;

@Service
public class IntrospectionService {

	private final AccessTokenRepository accessTokenRepository;

	private final JwtUtil jwtUtil;

	public IntrospectionService(JwtUtil jwtUtil, AccessTokenRepository accessTokenRepository) {

		this.jwtUtil = jwtUtil;

		this.accessTokenRepository = accessTokenRepository;

	}

	public IntrospectionResponse introspect(String token) {

		if (!jwtUtil.isValid(token)) {

			IntrospectionResponse response = new IntrospectionResponse();
			response.setActive(false);

			return response;

		}

		IntrospectionResponse response = new IntrospectionResponse();

		response.setActive(true);

		response.setClientId(jwtUtil.extractClientId(token));

		response.setUsername(jwtUtil.extractUsername(token));

		response.setScope(jwtUtil.extractScope(token));

		response.setExp(jwtUtil.extractExpiration(token));

		return response;

	}

}