package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción para cuando se va cambiar el estado de una receta a false y ya ha sido cambiada anteriormente
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class RecipeStatusException extends RuntimeException{


	private static final long serialVersionUID = 8108788305170680555L;

	public RecipeStatusException(Integer id) {
		super("La receta con id "+id+" ya ha sido aprobada por el administrador.");
		
	}
	
	

}
