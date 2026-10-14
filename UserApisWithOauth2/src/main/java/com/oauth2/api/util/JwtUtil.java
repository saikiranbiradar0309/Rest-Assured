package com.oauth2.api.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {


	private final long expiration;
	
	private final KeyLoader keyLoader;

	

	public JwtUtil(
	        KeyLoader keyLoader,
	        @Value("${jwt.expiration}") long expiration) {

	    this.keyLoader = keyLoader;
	    this.expiration = expiration * 1000;
	}
	
	/*
	 * Password Grant JWT
	 */

	public String generateAccessToken(
	        String username,
	        String clientId,
	        String scope,
	        List<String> roles,
	        List<String> permissions)
	{
		return Jwts.builder()

		        .subject(username)

		        .claim("client_id", clientId)

		        .claim("scope", scope)

		        .claim("roles", roles)

		        .claim("permissions", permissions)
		        
		        .issuedAt(new Date())

		        .expiration(
		                new Date(System.currentTimeMillis() + expiration))

		        .signWith(
		                keyLoader.getPrivateKey(),
		                io.jsonwebtoken.SignatureAlgorithm.RS256)

		        .compact();
	}
	
	public List<String> extractPermissions(String token) {

	    Claims claims = extractAllClaims(token);

	    return claims.get("permissions", List.class);
	}
	

	/*
	 * Client Credentials JWT
	 */
	public String generateClientCredentialsToken(String clientId, String scope) {

		return Jwts.builder()

				.subject(clientId)

				.claim("client_id", clientId)

				.claim("scope", scope)

				.claim("grant_type", "client_credentials")

				.issuedAt(new Date())

				.expiration(new Date(System.currentTimeMillis() + expiration))

				.signWith(
				        keyLoader.getPrivateKey(),
				        io.jsonwebtoken.SignatureAlgorithm.RS256)
				
				.compact();
	}

	/*
	 * Extract Username
	 */
	public String extractUsername(String token) {

		return extractAllClaims(token).getSubject();

	}

	/*
	 * Extract Client Id
	 */
	public String extractClientId(String token) {

		return extractAllClaims(token).get("client_id", String.class);

	}

	/*
	 * Extract Scope
	 */
	public String extractScope(String token) {

		return extractAllClaims(token).get("scope", String.class);

	}

	/*
	 * Validate Token
	 */
	public boolean validateToken(String token, UserDetails userDetails) {

		String username = extractUsername(token);

		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

	}

	/*
	 * Check Expiry
	 */
	public boolean isTokenExpired(String token) {

		return extractAllClaims(token).getExpiration().before(new Date());

	}

	public long extractExpiration(String token) {

		return extractAllClaims(token).getExpiration().getTime() / 1000;
	}

	public boolean isValid(String token) {

		try {

			extractAllClaims(token);

			return !isTokenExpired(token);

		} catch (Exception ex) {

			return false;

		}

	}

	public List<String> extractRoles(String token) {

		Claims claims = extractAllClaims(token);

		return claims.get("roles", List.class);
	}

	/*
	 * Read JWT Claims
	 */
	private Claims extractAllClaims(String token) {

	    return Jwts.parser()

	            .verifyWith(keyLoader.getPublicKey())

	            .build()

	            .parseSignedClaims(token)

	            .getPayload();
	}
}