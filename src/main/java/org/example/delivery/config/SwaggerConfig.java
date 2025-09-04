package org.example.delivery.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
            .title("배달 서비스 애플리케이션")
            .description("회원 가입, 로그인, 배달 조회, 배달 주문 수정 서비스")
            .version("v0.0.1");

        SecurityScheme apiKey = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .in(SecurityScheme.In.HEADER)
            .name("Authorization")
            .scheme("bearer")
            .bearerFormat("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement()
            .addList("Bearer Token");

        return new OpenAPI()
            .info(info)
            .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
            .addSecurityItem(securityRequirement)
            ;
    }
}
