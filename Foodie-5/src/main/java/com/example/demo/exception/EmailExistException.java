package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6671077284268418162L;

	public EmailExistException(String email) {
		super("El email "+email+" ya se encuentra registrado.");
	}
	
	

}
