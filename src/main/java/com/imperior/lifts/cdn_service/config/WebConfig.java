package com.imperior.lifts.cdn_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("userProfileImages/**" , "staticFiles/**", "Invoices/**")
                .addResourceLocations("file:./src/userProfileImages/", "file:./src/staticFiles/", "file:/app/userProfileImages/","file:/app/Invoices/", "file:/app/staticFiles/");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:8080",
                        "http://spring-boot-app"

                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
