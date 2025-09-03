package org.example.delivery.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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

        return new OpenAPI()
            .info(info);
    }
}
