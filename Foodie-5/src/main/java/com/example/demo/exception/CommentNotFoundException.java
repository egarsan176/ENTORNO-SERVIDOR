package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción para cuando no se encuentra un comentario por su id
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5129542756870398671L;
	
	public CommentNotFoundException(Integer id) {
		super("No existe ningún comentario con id "+id+".");
		
	}

}
