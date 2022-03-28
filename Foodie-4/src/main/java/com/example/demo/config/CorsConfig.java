package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				//para hacer el registro y el login
				registry.addMapping("/auth/**")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para comprobar que el token sea válido
				registry.addMapping("/login")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/user/**")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para comprobar que el email no existe en la base de datos
				registry.addMapping("/user/{email}")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para gestionar las recetas 
				registry.addMapping("/recipes")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para gestionar las recetas 
				registry.addMapping("/recipes/{id}")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//gestión de lineas
				registry.addMapping("/recipes/{id}/ingredientLine/{idLine}")
				.allowedOrigins("https://egarsan176.github.io")
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				//gestión de lineas
				registry.addMapping("/recipes/{id}/ingredientLine/**")
				.allowedOrigins("https://egarsan176.github.io")
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//gestión de archivos
				registry.addMapping("/upload")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("POST", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/files")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				registry.addMapping("/file/**")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/files/{id}")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/files/{name}")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/mostrar/**")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/category/**")
				.allowedOrigins("https://egarsan176.github.io")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
			}
			
		};
	}

}
