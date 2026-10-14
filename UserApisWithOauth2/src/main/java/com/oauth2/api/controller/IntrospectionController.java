package com.oauth2.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oauth2.api.dto.IntrospectionRequest;
import com.oauth2.api.dto.IntrospectionResponse;
import com.oauth2.api.service.IntrospectionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/oauth2")
public class IntrospectionController {

	private final IntrospectionService service;

	public IntrospectionController(IntrospectionService service) {

		this.service = service;

	}

//	@PostMapping("/introspect")
//	public ResponseEntity<IntrospectionResponse> introspect(
//
//			@Valid @RequestBody IntrospectionRequest request) {
//
//		return ResponseEntity.ok(
//
//				service.introspect(request.getToken()));
//
//	}

}