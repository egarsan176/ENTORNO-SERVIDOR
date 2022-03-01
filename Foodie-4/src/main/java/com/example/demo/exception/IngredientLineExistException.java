package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.model.Ingredient;

@ResponseStatus(HttpStatus.CONFLICT)
public class IngredientLineExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4305982071261583664L;

	public IngredientLineExistException(String ingredient) {
		super("Ya existe una línea de ingredientes que contiene "+ingredient+".");
	}
	
	

}
