package com.oauth2.api.scheduler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.oauth2.api.repository.AccessTokenRepository;
import com.oauth2.api.repository.RefreshTokenRepository;

@Component
public class TokenCleanupScheduler {

	private final AccessTokenRepository accessRepository;
	private final RefreshTokenRepository refreshRepository;
	private static final Logger log = LoggerFactory.getLogger(TokenCleanupScheduler.class);

	public TokenCleanupScheduler(AccessTokenRepository accessRepository, RefreshTokenRepository refreshRepository) {

		this.accessRepository = accessRepository;
		this.refreshRepository = refreshRepository;
	}

	@Scheduled(cron = "0 0 * * * *")
	public void cleanup() {

		accessRepository.deleteByExpiresAtBefore(LocalDateTime.now());

		refreshRepository.deleteByExpiresAtBefore(LocalDateTime.now());

		log.info("Expired access and refresh tokens cleaned.");

	}
}