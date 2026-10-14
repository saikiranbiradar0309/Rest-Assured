package com.oauth2.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UserApisWithOauth2Application {

	public static void main(String[] args) {

		SpringApplication.run(UserApisWithOauth2Application.class, args);

	}

}
