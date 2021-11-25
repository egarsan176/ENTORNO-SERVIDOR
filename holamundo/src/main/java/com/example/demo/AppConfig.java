package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {	//la clase se tiene que llamar sí o sí AppConfig y sustituye al fichero de configuración XML
							//esta clase la controla el iOc

	@Bean
	public HolaMundo holaMundo() {
		return new HolaMundo();
	}
	//creamos un método para cada bean que nos va a devolver un objeto 
	//en el momento en el que se arranca la aplicación, el bean se crea
	
	
	//el mensaje del HolaMundo se muestra porque tiene el mensaje en el constructor y por eso cuando Spring crea los beans se muestra el HolaMundo primero
	
	
	@Bean
	public DependantService dependantService() {			
		DependantService ds = new DependantService();
		ds.setService1(service1());
		ds.setService2(service2());
		
		return ds;
	}
	
	@Bean
	public Service1 service1() {	//lo que hace es llamar al constructor de la clase
		return new Service1();
	}
	
	@Bean
	public Service2 service2() {
		return new Service2();
	}
}
