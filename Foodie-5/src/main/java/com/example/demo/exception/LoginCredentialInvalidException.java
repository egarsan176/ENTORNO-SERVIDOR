package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción para cuando falta uno de los campos necesarios para hacer login
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class LoginCredentialInvalidException extends RuntimeException{


	private static final long serialVersionUID = -6759868980741555974L;

	public LoginCredentialInvalidException() {
		super("Falta uno de los campos necesarios para iniciar sesión");
		
	}
	
	

}
