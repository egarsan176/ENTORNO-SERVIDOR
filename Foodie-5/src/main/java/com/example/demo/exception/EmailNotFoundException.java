package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción para cuando no se encuentra un email
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7753878785751971916L;

	public EmailNotFoundException() {
		super("El email es incorrecto");
	}

}
