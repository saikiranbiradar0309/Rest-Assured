package com.oauth2.api.service;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oauth2.api.dto.ClientSecretResponse;
import com.oauth2.api.dto.OAuthClientRequest;
import com.oauth2.api.dto.OAuthClientResponse;
import com.oauth2.api.entity.OAuthClient;
import com.oauth2.api.exception.ClientNotFoundException;
import com.oauth2.api.exception.DuplicateClientException;
import com.oauth2.api.repository.OAuthClientRepository;

@Service
public class OAuthClientService {

	private final OAuthClientRepository repository;
	private final PasswordEncoder passwordEncoder;

	public OAuthClientService(OAuthClientRepository repository, PasswordEncoder passwordEncoder) {

		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public OAuthClientResponse create(OAuthClientRequest request) {

		if (repository.existsByClientId(request.getClientId())) {
			throw new DuplicateClientException("Client already exists");
		}

		OAuthClient client = new OAuthClient();

		client.setClientId(request.getClientId());

		client.setClientSecret(passwordEncoder.encode(request.getClientSecret()));

		client.setClientName(request.getClientName());

		client.setGrantTypes(request.getGrantTypes());

		client.setScopes(request.getScopes());

		client.setAccessTokenValidity(request.getAccessTokenValidity());

		client.setRefreshTokenValidity(request.getRefreshTokenValidity());

		client.setEnabled(true);

		repository.save(client);

		// prepare response
		OAuthClientResponse response = map(client);

		// show original secret only during registration
		response.setClientSecret(request.getClientSecret());

		return response;

	}

	public List<OAuthClientResponse> findAll() {

		return repository.findAll().stream().map(this::map).toList();
	}

	public OAuthClientResponse findById(Long id) {

		OAuthClient client = repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

		return map(client);
	}

	public OAuthClientResponse update(Long id, OAuthClientRequest request) {

		OAuthClient client = repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

		client.setClientName(request.getClientName());
		client.setGrantTypes(request.getGrantTypes());
		client.setScopes(request.getScopes());
		client.setAccessTokenValidity(request.getAccessTokenValidity());
		client.setRefreshTokenValidity(request.getRefreshTokenValidity());

		repository.save(client);

		return map(client);
	}

	public void delete(Long id) {

		OAuthClient client = repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

		repository.delete(client);
	}

	public OAuthClientResponse enable(Long id) {

		OAuthClient client = repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

		client.setEnabled(true);

		repository.save(client);

		return map(client);
	}

	public OAuthClientResponse disable(Long id) {

		OAuthClient client = repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

		client.setEnabled(false);

		repository.save(client);

		return map(client);
	}

	@Transactional
	public ClientSecretResponse rotateSecret(Long id) {

		OAuthClient client = repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

		String newSecret = generateSecret();

		client.setClientSecret(passwordEncoder.encode(newSecret));

		repository.save(client);

		ClientSecretResponse clientResponse = new ClientSecretResponse();

		clientResponse.setClientId(client.getClientId());
		clientResponse.setClientSecret(newSecret);

		return clientResponse;

	}

	private String generateSecret() {

		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 32; i++) {

			sb.append(chars.charAt(random.nextInt(chars.length())));

		}

		return sb.toString();
	}

	private OAuthClientResponse map(OAuthClient client) {

		OAuthClientResponse response = new OAuthClientResponse();

		response.setId(client.getId());

		response.setClientId(client.getClientId());

		response.setClientSecret(client.getClientSecret());

		response.setClientName(client.getClientName());

		response.setGrantTypes(client.getGrantTypes());

		response.setAccessTokenValidity(client.getAccessTokenValidity());

		response.setRefreshTokenValidity(client.getRefreshTokenValidity());

		response.setEnabled(client.getEnabled());

		return response;

	}

}