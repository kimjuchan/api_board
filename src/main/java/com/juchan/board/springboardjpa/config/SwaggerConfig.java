package com.juchan.board.springboardjpa.config;



import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        //실행 순서 test
        System.out.println("##SwaggerConfig setting....");

        final Info info = new Info()
                .title("Board API")
                .version("v1.0.0")
                .description("기본 Rest API 개발 방식 진행.");

        return new OpenAPI()
                .info(info);

    }

}
