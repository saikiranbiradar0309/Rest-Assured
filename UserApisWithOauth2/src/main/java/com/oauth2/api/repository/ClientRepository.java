package com.oauth2.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauth2.api.entity.OAuthClient;

public interface ClientRepository extends JpaRepository<OAuthClient, Long> {

	Optional<OAuthClient> findByClientId(String clientId);

}