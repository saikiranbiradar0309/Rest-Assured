package com.oauth2.api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oauth2.api.dto.TokenResponse;
import com.oauth2.api.entity.OAuthClient;
import com.oauth2.api.entity.RefreshToken;
import com.oauth2.api.exception.InvalidGrantException;
import com.oauth2.api.repository.AccessTokenRepository;
import com.oauth2.api.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshRepository;
    private final AccessTokenRepository accessRepository;
    private final TokenService tokenService;

    public RefreshTokenService(
            RefreshTokenRepository refreshRepository,
            AccessTokenRepository accessRepository,
            TokenService tokenService) {

        this.refreshRepository = refreshRepository;
        this.accessRepository = accessRepository;
        this.tokenService = tokenService;
    }

    @Transactional
    public TokenResponse refresh(String token) {

        RefreshToken refreshToken = refreshRepository
                .findByRefreshToken(token)
                .orElseThrow(InvalidGrantException::new);

        if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            refreshRepository.delete(refreshToken);
            throw new InvalidGrantException();
        }

        accessRepository.deleteByUsername(refreshToken.getUsername());

        refreshRepository.delete(refreshToken);

        return tokenService.createToken(
                refreshToken.getClient(),
                refreshToken.getUsername(),
                refreshToken.getClient().getScopes());
    }
    
}