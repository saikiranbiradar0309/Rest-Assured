package com.employee.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // enables @PreAuthorize in controller
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		// USER role
		manager.createUser(User.withUsername("user").password(encoder.encode("user123")).roles("USER").build());

		// ADMIN role
		manager.createUser(User.withUsername("admin").password(encoder.encode("admin123")).roles("ADMIN").build());

		return manager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            // Swagger UI and OpenAPI endpoints (public)
	            .requestMatchers(
	                "/swagger-ui.html",
	                "/swagger-ui/**",
	                "/v3/api-docs/**",
	                "/v3/api-docs.yaml",
	                "/swagger-resources/**",
	                "/webjars/**",
	                "/swagger-ui/index.html",
	                "/h2-console/**"
	            ).permitAll()
	            // Everything else requires authentication
	            .anyRequest().authenticated()
	        )
	        .httpBasic();

	    return http.build();
	}

}