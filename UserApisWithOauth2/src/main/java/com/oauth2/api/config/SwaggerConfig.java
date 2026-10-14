package com.oauth2.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openAPI() {

		return new OpenAPI()

				.info(

						new Info()

								.title("OAuth2 Authorization Server API")

								.version("1.0")

								.description("Custom OAuth2 Authorization Server")

								.contact(

										new Contact()

												.name("Praful Pawar")

												.email("praful@example.com"))

								.license(

										new License()

												.name("Apache 2.0")));

	}
}