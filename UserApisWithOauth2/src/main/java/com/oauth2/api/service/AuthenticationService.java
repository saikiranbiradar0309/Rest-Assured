package com.oauth2.api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void authenticate(String username, String password) {

        try {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(username, password));

            System.out.println("================================");
            System.out.println("Authenticated : " + authentication.isAuthenticated());
            System.out.println("Principal     : " + authentication.getPrincipal());
            System.out.println("Authorities   : " + authentication.getAuthorities());
            System.out.println("================================");

        } catch (Exception ex) {

            ex.printStackTrace();

            throw ex;
        }
    }
}