package com.oauth2.api.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauth2.api.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByRefreshToken(String refreshToken);

	void deleteByUsername(String username);

	void deleteByExpiresAtBefore(LocalDateTime time);

}