package com.oauth2.api.resource.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration

@SecurityScheme(

        name="BearerAuth",

        type=SecuritySchemeType.HTTP,

        scheme="bearer",

        bearerFormat="JWT")

public class SwaggerSecurityConfig {

}