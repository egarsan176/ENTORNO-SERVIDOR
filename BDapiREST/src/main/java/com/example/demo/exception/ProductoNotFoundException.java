package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7351351970433179158L;

	public ProductoNotFoundException (){
		super("No se puede encontrar el producto seleccionado");
	}
}
