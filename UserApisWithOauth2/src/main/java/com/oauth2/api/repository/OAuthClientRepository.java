package com.oauth2.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth2.api.entity.OAuthClient;

@Repository
public interface OAuthClientRepository extends JpaRepository<OAuthClient, Long> {

	Optional<OAuthClient> findByClientId(String clientId);

	Optional<OAuthClient> findByClientIdAndClientSecret(String clientId, String clientSecret);

    boolean existsByClientId(String clientId);

    
}
