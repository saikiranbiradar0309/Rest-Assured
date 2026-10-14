package com.oauth2.api.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    public String generateAccessToken() {

        return UUID.randomUUID().toString().replace("-", "");

    }

    public String generateRefreshToken() {

        return UUID.randomUUID().toString().replace("-", "");

    }

}