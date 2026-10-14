package com.oauth2.api.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {

        return new OpenAPI()

                .info(

                        new Info()

                                .title("Employee Resource Server")

                                .version("1.0")

                                .description("Protected Resource APIs"));
    }

}