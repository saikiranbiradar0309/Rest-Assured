package com.oauth2.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oauth2.api.filter.BearerTokenFilter;
import com.oauth2.api.security.CustomUserDetailsService;
import com.oauth2.api.security.JwtAuthenticationEntryPoint;
import com.oauth2.api.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtFilter;

	private final JwtAuthenticationEntryPoint entryPoint;

	//private final BearerTokenFilter bearerTokenFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtFilter, JwtAuthenticationEntryPoint entryPoint) {

		this.jwtFilter = jwtFilter;
		this.entryPoint = entryPoint;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider authenticationProvider)
			throws Exception {

		http

				.csrf(csrf -> csrf.disable())

				.authenticationProvider(authenticationProvider)

				.exceptionHandling(ex ->

				ex.authenticationEntryPoint(entryPoint))

				.authorizeHttpRequests(auth -> auth
				        .anyRequest().permitAll()
				)
				
//				.authorizeHttpRequests(auth -> auth
//
//						.requestMatchers("/oauth2/token", "/oauth2/refresh", "/oauth2/introspect", "/oauth2/revoke")
//
//						.permitAll()
//
//						.anyRequest()
//
//						.authenticated())

				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

				//.addFilterBefore(bearerTokenFilter, UsernamePasswordAuthenticationFilter.class)

				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);

		return provider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();

	}

}
