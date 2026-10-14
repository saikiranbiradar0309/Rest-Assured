package com.oauth2.api.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauth2.api.entity.AccessToken;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

	Optional<AccessToken> findByAccessToken(String accessToken);

	void deleteByUsername(String username);

	void deleteByExpiresAtBefore(LocalDateTime time);

}