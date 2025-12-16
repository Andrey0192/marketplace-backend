package com.example.marketplace.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Marketplace Backend API",
                version = "0.1.0",
                description = "Simple marketplace backend (MVP CRUD)"
        )
)
public class OpenApiConfig {
}
