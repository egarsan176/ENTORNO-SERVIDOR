package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredienteLineNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7587088223348281825L;

	public IngredienteLineNotFoundException(Integer id) {
		super("No existe ninguna línea con id "+id+".");
	}
		
	
	

}
