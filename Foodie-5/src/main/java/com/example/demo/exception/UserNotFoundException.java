package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Excepción para cuando no se encuentra a un usuario por su id
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 2011517689050756541L;

	public UserNotFoundException(Long id) {
		super("No se encuentra al usuario con id: "+id+".");
	}
	
	public UserNotFoundException() {
		super("No existe el usuario");
	}
}
