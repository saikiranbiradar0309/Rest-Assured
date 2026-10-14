package com.oauth2.api.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.oauth2.api.entity.AccessToken;
import com.oauth2.api.security.CustomUserDetailsService;
import com.oauth2.api.service.TokenValidationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BearerTokenFilter extends OncePerRequestFilter {

    private final TokenValidationService tokenValidationService;
    private final CustomUserDetailsService userDetailsService;

    public BearerTokenFilter(
            TokenValidationService tokenValidationService,
            CustomUserDetailsService userDetailsService) {

        this.tokenValidationService = tokenValidationService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("===== Bearer Filter =====");

        String header = request.getHeader("Authorization");

        System.out.println("Authorization Header : " + header);

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            System.out.println("Access Token : " + token);

            AccessToken accessToken =
                    tokenValidationService.validate(token);

            System.out.println("DB Token : " + accessToken.getAccessToken());

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(accessToken.getUsername());

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Authentication Set Successfully");
        }

        filterChain.doFilter(request, response);
    }
}