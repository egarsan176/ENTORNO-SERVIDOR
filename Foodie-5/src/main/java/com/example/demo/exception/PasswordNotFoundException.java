package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción para cuando no se encuentra una contraseña
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PasswordNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5252114757177404005L;

	public PasswordNotFoundException() {
		super("La contraseña es incorrecta");
	}

}
