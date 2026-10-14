package com.oauth2.api.resource.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private final KeyLoader keyLoader;

    public JwtUtil(KeyLoader keyLoader) {
        this.keyLoader = keyLoader;
    }

    /*
     * Read all JWT claims
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(keyLoader.getPublicKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }

    /*
     * Username
     */
    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();
    }

    /*
     * Scope
     */
    public String extractScope(String token) {

        return extractAllClaims(token).get("scope", String.class);
    }

    /*
     * Roles
     */
    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {

        return (List<String>) extractAllClaims(token).get("roles");
    }

    /*
     * Scopes List
     */
    public List<String> extractScopes(String token) {

        String scope = extractScope(token);

        if (scope == null || scope.isBlank()) {
            return List.of();
        }

        return Arrays.asList(scope.split(" "));
    }

    /*
     * Expiry Check
     */
    public boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    /*
     * Validate JWT
     */
    public boolean validateToken(String token) {

        return !isTokenExpired(token);
    }
}