package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción para cuando no se encuentra un fichero
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException{

	public FileNotFoundException(String message) {
		super("No existe el fichero "+message +".");
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3667913565500115899L;

}
