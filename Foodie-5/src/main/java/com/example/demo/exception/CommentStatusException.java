package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CommentStatusException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6725643788682677286L;

	public CommentStatusException(Integer id) {
		super("El comentario con id "+id+" ya ha sido aprobado por el administrador.");
		
	}
}
