package com.example.mission02.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info().title("도서관 서버 API 명세서").version("v1")
                .description("항해99 19기 과제2 (송유하, 김수환)");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
