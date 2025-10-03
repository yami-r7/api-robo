package br.com.senai.robo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("https://api-robo-production.up.railway.app"))
                .info(new Info()
                        .title("API Robô SENAI")
                        .version("v1")
                        .description("API RESTful para o gerenciamento e monitoramento de robôs, suas ações e obstáculos detectados.")
                        .termsOfService("http://example.com/terms")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}