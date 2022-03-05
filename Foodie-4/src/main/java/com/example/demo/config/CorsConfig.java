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
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para comprobar que el token sea válido
				registry.addMapping("/login")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/user/**")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para comprobar que el email no existe en la base de datos
				registry.addMapping("/user/{email}")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para gestionar las recetas 
				registry.addMapping("/recipes")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para gestionar las recetas 
				registry.addMapping("/recipes/{id}")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//gestión de lineas
				registry.addMapping("/recipes/{id}/ingredientLine/{idLine}")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				//gestión de lineas
				registry.addMapping("/recipes/{id}/ingredientLine/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//gestión de archivos
				registry.addMapping("/upload")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("POST", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/files")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				registry.addMapping("/file/**")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/files/{id}")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/files/{name}")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/mostrar/**")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/category/**")
				.allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
			}
			
		};
	}

}
