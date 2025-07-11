package com.ouahoudi.library.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "api-key";
        return new OpenAPI().info(new Info().title("Bookstore API").version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName)).components(
                        new io.swagger.v3.oas.models.Components().addSecuritySchemes(securitySchemeName,
                                new SecurityScheme().name("api-key").type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER)));
    }
}

