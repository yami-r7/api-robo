package br.com.senai.robo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a configuração a TODOS os endpoints da API
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://127.0.0.1:5500",
                        "https://api-robo-production.up.railway.app",
                        "http://localhost:8081"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"); // Métodos HTTP permitidos
    }
}