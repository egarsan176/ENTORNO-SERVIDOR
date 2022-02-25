package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredientLineNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7587088223348281825L;

	public IngredientLineNotFoundException(Integer id) {
		super("No existe ninguna línea con id "+id+".");
	}
		
	
	

}
