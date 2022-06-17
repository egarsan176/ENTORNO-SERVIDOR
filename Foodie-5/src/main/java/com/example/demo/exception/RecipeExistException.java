package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción para cuando ya existe una receta con ese nombre
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class RecipeExistException extends RuntimeException{


	private static final long serialVersionUID = -9133521367942455425L;

	public RecipeExistException(String recipeName) {
		super("Ya existe una receta de nombre "+recipeName+".");
	}
	
	

}
