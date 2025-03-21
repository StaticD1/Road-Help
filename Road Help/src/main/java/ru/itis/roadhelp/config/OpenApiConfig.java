package ru.itis.roadhelp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Road Help API")
                        .version("1.0")
                        .description("API for Road Help"));

    }

    @PostConstruct
    public void init() {
        System.out.println("OpenApiConfig initialized");
    }
}
