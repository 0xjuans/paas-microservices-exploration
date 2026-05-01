package com.paas.catalog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Metadatos OpenAPI / Swagger UI para catalog-service. */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI catalogOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catalog Service API")
                        .description("Catálogo de productos persistido en PostgreSQL.")
                        .version("1.0"));
    }
}
