package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HolamundoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(HolamundoApplication.class, args); //guardamos el contexto de la aplicación en una variable
		
		((DependantService) ctx.getBean("dependantService")).doSmth();
	}

}
