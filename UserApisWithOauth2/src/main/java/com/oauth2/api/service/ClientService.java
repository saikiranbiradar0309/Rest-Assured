package com.oauth2.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oauth2.api.entity.OAuthClient;
import com.oauth2.api.exception.ClientDisabledException;
import com.oauth2.api.exception.InvalidClientException;
import com.oauth2.api.repository.ClientRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;
	private final PasswordEncoder passwordEncoder;

	public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {

		this.clientRepository = clientRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public OAuthClient validateClient(String clientId, String clientSecret) {

		if (clientId == null || clientId.isBlank()) {

			throw new InvalidClientException("client_id is required");
		}

		if (clientSecret == null || clientSecret.isBlank()) {

			throw new InvalidClientException("client_secret is required");
		}

		OAuthClient client = clientRepository.findByClientId(clientId)

				.orElseThrow(() -> new InvalidClientException("Invalid client id"));

		if (!Boolean.TRUE.equals(client.getEnabled())) {

			throw new ClientDisabledException("Client is disabled");
		}

		if (!passwordEncoder.matches(clientSecret, client.getClientSecret())) {

			throw new InvalidClientException("Invalid client secret");
		}

		return client;
	}

}