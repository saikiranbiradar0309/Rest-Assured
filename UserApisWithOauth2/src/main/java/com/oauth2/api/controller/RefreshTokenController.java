package com.oauth2.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oauth2.api.dto.RefreshRequest;
import com.oauth2.api.dto.TokenResponse;
import com.oauth2.api.service.RefreshTokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/oauth2")
public class RefreshTokenController {

    private final RefreshTokenService service;

    public RefreshTokenController(
            RefreshTokenService service) {

        this.service = service;
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(

            @Valid
            @RequestBody
            RefreshRequest request) {

        return ResponseEntity.ok(

                service.refresh(

                        request.getRefreshToken()));

    }

}