package com.server.slosaeng.global.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI ServerApiTest() {
		Info info = new Info()
			.title("slo-saeng server api")
			.description("slo-saeng server api 문서입니다.")
			.version("v1.0.0");
		SecurityScheme securityScheme = new SecurityScheme()
			.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
			.in(SecurityScheme.In.HEADER).name("Authorization");
		SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

		return new OpenAPI()
			.addServersItem(new Server().url("/"))
			.info(info)
			.components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
			.security(Arrays.asList(securityRequirement));
	}
}
