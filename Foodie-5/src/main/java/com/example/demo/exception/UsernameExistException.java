package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6322600920312914227L;

	public UsernameExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsernameExistException(String username) {
		super("El username "+username+" ya se encuentra registrado.");
	}
	
	

}
