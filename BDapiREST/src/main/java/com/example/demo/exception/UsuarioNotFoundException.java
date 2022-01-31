package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1265797241996642725L;

	public UsuarioNotFoundException(Long id) {
		super("No se puede encontrar el usuario con ID: " + id);
	}
}
