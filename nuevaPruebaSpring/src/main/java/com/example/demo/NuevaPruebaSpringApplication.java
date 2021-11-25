package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NuevaPruebaSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(NuevaPruebaSpringApplication.class, args);
		
		((DependantService) ctx.getBean("dependantService")).doSmth();		//el nombre de un bean será siempre el nombre de la clase con la primera letra en minúscula
																			//los beans son elementos que están vivos en el contexto, por eso se puede acceder a ellos
	}

}


