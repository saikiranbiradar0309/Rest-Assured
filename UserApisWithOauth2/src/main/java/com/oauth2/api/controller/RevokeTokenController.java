package com.oauth2.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.api.dto.RevokeTokenRequest;
import com.oauth2.api.service.RevokeTokenService;

@RestController
@RequestMapping("/oauth2")
public class RevokeTokenController {

	private final RevokeTokenService revokeTokenService;

	public RevokeTokenController(RevokeTokenService revokeTokenService) {
		this.revokeTokenService = revokeTokenService;
	}

//	@PostMapping("/revoke")
//	public ResponseEntity<Void> revoke(@RequestBody RevokeTokenRequest request) {
//
//		revokeTokenService.revoke(request.getClientId(), request.getClientSecret(), request.getAccessToken());
//
//		return ResponseEntity.ok().build();
//	}
//	
}