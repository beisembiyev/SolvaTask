package com.example.solva.Configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Sample API for transaction microservice")
                        .description("Transactions management application")
                        .version("v1.0.0").license(new License().name("Apache 2.0")
                                .url("http://springdoc.org")).contact(new Contact().name("beisembiyev")
                                .email("baha.applecity@gmail.com")));
    }
}
