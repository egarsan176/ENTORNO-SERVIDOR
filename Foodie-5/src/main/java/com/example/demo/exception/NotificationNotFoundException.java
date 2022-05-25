package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotificationNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7038261505614038610L;

	public NotificationNotFoundException(Integer id) {
		super("No existe ninguna notificación con id "+id+".");
		// TODO Auto-generated constructor stub
	}
	
	

}
