package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9072494968168899814L;

	public CategoryNotFoundException(Integer id) {
		super("No existe ninguna categoría con id "+id+".");
	}

	

}
