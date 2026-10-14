package com.oauth2.api.service;

import org.springframework.stereotype.Service;

import com.oauth2.api.repository.AccessTokenRepository;
import com.oauth2.api.repository.RefreshTokenRepository;

@Service
public class TokenRevocationService {

    private final AccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenRevocationService(
            AccessTokenRepository accessTokenRepository,
            RefreshTokenRepository refreshTokenRepository) {

        this.accessTokenRepository = accessTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void revoke(String token) {

        accessTokenRepository.findByAccessToken(token)
                .ifPresent(accessTokenRepository::delete);

        refreshTokenRepository.findByRefreshToken(token)
                .ifPresent(refreshTokenRepository::delete);
    }
}