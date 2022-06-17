package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción para cuando no se encuentra un ingrediente por su id
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredientNotFoundException extends RuntimeException {



	/**
	 * 
	 */
	private static final long serialVersionUID = 8178535872826808666L;

	public IngredientNotFoundException(Integer id) {
		super("No existe ninguna ingrediente con id "+id+".");
		
	}

	

}
