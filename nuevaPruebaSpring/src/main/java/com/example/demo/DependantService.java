package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class DependantService {
	
	 //CASO 1: cuando el inversor de control llega crea un bean y ejecuta su constructor	
		//cuando tenemos un bean que se tiene que inyectar y no puede cambiar en ningún momento lo hacemos por constructor o en variables

//	private final Service1 service1; 
//	private final Service2 service2;
	
//	@Autowired		//no tenemos fichero appConfig que especifique que service1 y service2 son bean
//	public DependantService(Service1 service1, Service2 service2) {
//		this.service1 = service1;
//		this.service2 = service2;
//	}
	
	 //CASO 2: con setters
		//si puede cambiar o bien tiene asociada alguna lógica de negocio lo haremos mediante setters
	
//	private Service1 service1;
//	private Service2 service2;
//	
//	@Autowired
//	public void setService1(Service1 service1) {
//		this.service1 = service1;
//	}
//
//	@Autowired
//	public void setService2(Service2 service2) {
//		this.service2 = service2;
//	}
	
		//CASO 3: por atributos.
			//es como una inyección por constructor pero sin usar constructor
	@Autowired
	private Service1 service1;
	@Autowired
	private Service2 service2;


	void doSmth() {
		service1.doStmth();
		service2.doStmth();
	}
	
	
	
	

}
