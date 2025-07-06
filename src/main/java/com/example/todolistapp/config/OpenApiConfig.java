package com.example.todolistapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
            title = "To-do list System Api",
            description = "API приложения для управления задачами",
            version = "1.0.0",
            contact = @Contact(
                    name = "Zayats Natalia",
                    url = "https://github.com/zayats74"
            )
        )
)
public class OpenApiConfig {
}
