package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6958898336785508083L;

	public PedidoNotFoundException(Integer id) {
		super("No se puede encontrar el pedido con ID: " + id);
	}
}
