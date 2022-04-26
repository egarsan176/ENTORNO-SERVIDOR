package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserDeleteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5710322168251280971L;

	public UserDeleteException(Long id) {
		super("El usuario con id: "+id+", es administrador del sistema y por tanto no se puede eliminar.");
	}
}
