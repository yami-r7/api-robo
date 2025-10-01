package br.com.senai.robo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para a documentação do OpenAPI (Swagger).
 */
@Configuration
public class OpenApiConfig {

    /**
     * Cria um Bean do tipo OpenAPI com as informações personalizadas da API.
     * @return um objeto OpenAPI configurado.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Robôs - SENAI")
                        .version("v1")
                        .description("API para gerenciamento de robôs.")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}