package com.oauth2.api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.oauth2.api.entity.AccessToken;
import com.oauth2.api.repository.AccessTokenRepository;

@Service
public class TokenValidationService {

    private final AccessTokenRepository repository;

    public TokenValidationService(AccessTokenRepository repository) {
        this.repository = repository;
    }

    public AccessToken validate(String token) {

        AccessToken accessToken = repository
                .findByAccessToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Invalid Access Token"));

        if (accessToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Access Token Expired");
        }

        return accessToken;
    }

}