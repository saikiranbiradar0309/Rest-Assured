package com.oauth2.api.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oauth2.api.resource.security.JwtAuthenticationEntryPoint;
import com.oauth2.api.resource.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
			JwtAuthenticationEntryPoint authenticationEntryPoint) {

		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())

	        .sessionManagement(session ->
	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

	        .exceptionHandling(exception ->
	                exception.authenticationEntryPoint(authenticationEntryPoint))

	        .authorizeHttpRequests(auth -> auth

	                // Swagger
	                .requestMatchers(
	                        "/swagger-ui/**",
	                        "/swagger-ui.html",
	                        "/v3/api-docs/**",
	                        "/api-docs/**")
	                .permitAll()

	                // Public APIs
	                .requestMatchers("/public/**")
	                .permitAll()

	                // Protected APIs
	                .requestMatchers(
	                        "/employees/**",
	                        "/dashboard/**"
	                )
	                .authenticated()

	                // Everything else
	                .anyRequest()
	                .permitAll())

	        .addFilterBefore(
	                jwtAuthenticationFilter,
	                UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
}