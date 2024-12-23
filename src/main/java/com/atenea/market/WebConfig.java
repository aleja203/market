
package com.atenea.market;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas las rutas
                .allowedOrigins("http://localhost:3000") // Permite solicitudes desde el frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true); // Permite el uso de cookies o credenciales
    }
    
        @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return Jackson2ObjectMapperBuilder.json().indentOutput(true);
    }

    // Si deseas un ObjectMapper personalizado
    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json().indentOutput(true).build();
    }
    
}
