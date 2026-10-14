package com.oauth2.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oauth2.api.entity.AccessToken;
import com.oauth2.api.entity.OAuthClient;
import com.oauth2.api.exception.InvalidGrantException;
import com.oauth2.api.repository.AccessTokenRepository;
import com.oauth2.api.repository.RefreshTokenRepository;

@Service
public class RevokeTokenService {

    private final ClientService clientService;
    private final AccessTokenRepository accessRepository;
    private final RefreshTokenRepository refreshRepository;

    public RevokeTokenService(
            ClientService clientService,
            AccessTokenRepository accessRepository,
            RefreshTokenRepository refreshRepository) {

        this.clientService = clientService;
        this.accessRepository = accessRepository;
        this.refreshRepository = refreshRepository;
    }

    @Transactional
    public void revoke(
            String clientId,
            String clientSecret,
            String token) {

        // Validate OAuth Client
        OAuthClient client = clientService.validateClient(
                clientId,
                clientSecret);

        // Find Access Token
        AccessToken accessToken = accessRepository
                .findByAccessToken(token)
                .orElseThrow(() ->
                        new InvalidGrantException("Invalid Access Token"));

        // Verify token belongs to this client
        if (!client.getClientId().equals(accessToken.getClientId())) {
            throw new InvalidGrantException(
                    "Token does not belong to this client");
        }

        // Delete Access Token
        accessRepository.delete(accessToken);

        // Delete all Refresh Tokens for the user
        refreshRepository.deleteByUsername(
                accessToken.getUsername());
    }
}