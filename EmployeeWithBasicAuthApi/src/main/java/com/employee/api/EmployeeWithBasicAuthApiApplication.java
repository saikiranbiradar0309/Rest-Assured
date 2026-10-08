package com.employee.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.employee.api.entity")
@EnableJpaRepositories(basePackages = "com.employee.api.repository")
public class EmployeeWithBasicAuthApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeWithBasicAuthApiApplication.class, args);
	}

	


	
}
