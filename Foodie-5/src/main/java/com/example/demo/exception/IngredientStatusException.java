package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción para cuando se va cambiar el estado de un ingrediente a false y ya ha sido cambiado anteriormente
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class IngredientStatusException extends RuntimeException{

	private static final long serialVersionUID = -9045231956366231745L;

	public IngredientStatusException(Integer id) {
		super("El ingrediente con id "+id+" ya ha sido aprobado por el administrador.");
		
	}
	
	

}
