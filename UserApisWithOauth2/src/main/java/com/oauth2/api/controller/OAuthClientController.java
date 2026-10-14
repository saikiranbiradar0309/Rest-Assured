package com.oauth2.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.api.dto.ClientSecretResponse;
import com.oauth2.api.dto.OAuthClientRequest;
import com.oauth2.api.dto.OAuthClientResponse;
import com.oauth2.api.service.OAuthClientService;


@RestController
@RequestMapping("/oauth2/clients")
public class OAuthClientController {

	private final OAuthClientService service;

	public OAuthClientController(OAuthClientService service) {
		this.service = service;
	}

	@PostMapping("/{id}/rotate-secret")
	public ClientSecretResponse rotateSecret(@PathVariable Long id) {

		return service.rotateSecret(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OAuthClientResponse create(@RequestBody OAuthClientRequest request) {

		return service.create(request);
	}

	@GetMapping
	public List<OAuthClientResponse> findAll() {

		return service.findAll();
	}

	@GetMapping("/{id}")
	public OAuthClientResponse findById(@PathVariable Long id) {

		return service.findById(id);
	}

	@PutMapping("/{id}")
	public OAuthClientResponse update(@PathVariable Long id, @RequestBody OAuthClientRequest request) {

		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

		service.delete(id);
	}

	@PutMapping("/{id}/enable")
	public OAuthClientResponse enable(@PathVariable Long id) {

		return service.enable(id);
	}

	@PutMapping("/{id}/disable")
	public OAuthClientResponse disable(@PathVariable Long id) {

		return service.disable(id);
	}
}