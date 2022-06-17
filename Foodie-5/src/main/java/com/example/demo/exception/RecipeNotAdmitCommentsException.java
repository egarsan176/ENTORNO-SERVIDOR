package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Excepción para cuando una receta no admite comentarios
 * @author estefgar
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class RecipeNotAdmitCommentsException extends RuntimeException{

	private static final long serialVersionUID = -3853506892585376269L;

	public RecipeNotAdmitCommentsException(Integer idRecipe) {
		super("La receta con id "+idRecipe+" todavía no ha sido aprobada por el administrador y por lo tanto, no admite comentarios.");
	}
	
	
	

}
