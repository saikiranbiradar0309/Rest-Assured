package com.employee.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfig {

	public OpenAPI customerOpenApiDocs() {

		OpenAPI openApi = new OpenAPI();

		openApi.info(new Info().title("Employee API swagger documentations").version("Version 1.0"));

		return openApi;

	}

}
