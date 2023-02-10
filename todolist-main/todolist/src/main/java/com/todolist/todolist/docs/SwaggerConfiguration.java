package com.todolist.todolist.docs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI springToDoListOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("To Do List")
						.description("Aplicação desenvolvida para o projeto praticando start")
						.version("1.0.0"));
	}
}