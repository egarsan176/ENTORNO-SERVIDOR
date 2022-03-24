package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
	
	private String url= "http://localhost:4200";
	//url
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				//para hacer el registro y el login
				registry.addMapping("/auth/**")
				.allowedOrigins(url)
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para comprobar que el token sea válido
				registry.addMapping("/login")
				.allowedOrigins(url)
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/user/**")
				.allowedOrigins(url)
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para comprobar que el email no existe en la base de datos
				registry.addMapping("/user/{email}")
				.allowedOrigins(url)
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para comprobar que el username no existe en la base de datos
				registry.addMapping("/user/checkUsername/{username}")
				.allowedOrigins(url)
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para gestionar las recetas 
				registry.addMapping("/recipes")
				.allowedOrigins(url)
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//para gestionar las recetas 
				registry.addMapping("/recipes/{id}")
				.allowedOrigins(url)
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//gestión de lineas
				registry.addMapping("/recipes/{id}/ingredientLine/{idLine}")
				.allowedOrigins(url)
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				//gestión de lineas
				registry.addMapping("/recipes/{id}/ingredientLine/**")
				.allowedOrigins(url)
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//gestión de archivos
				registry.addMapping("/upload")
				.allowedOrigins(url)
                .allowedHeaders("POST", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/files")
				.allowedOrigins(url)
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				registry.addMapping("/file/**")
				.allowedOrigins(url)
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/files/{id}")
				.allowedOrigins(url)
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/files/{name}")
				.allowedOrigins(url)
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/mostrar/**")
				.allowedOrigins(url)
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				registry.addMapping("/category/**")
				.allowedOrigins(url)
                .allowedHeaders("GET", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				//GESTIONES DEL ADMIN
				registry.addMapping("/admin/**")
				.allowedOrigins(url)
                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
			}
			
			
			
		};
	}

}
