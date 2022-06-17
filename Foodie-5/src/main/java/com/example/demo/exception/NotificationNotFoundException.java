package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción para cuando no se encuentra una notificación por su id
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotificationNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -7038261505614038610L;

	public NotificationNotFoundException(Integer id) {
		super("No existe ninguna notificación con id "+id+".");

	}
	
	

}
