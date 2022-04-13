package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.RecipeNotFoundException;
import com.example.demo.exception.RecipeStatusException;
import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeService;
import com.fasterxml.jackson.databind.JsonMappingException;
/**
 * Esta clase es un controlador REST que intercepta peticiones al servidor, encargándose de las tareas del administrador
 * Para acceder a este controlador es necesario disponer del token y que el rol del usuario sea ADMIN
 * @author estefgar
 *
 */
@RestController
public class AdminController {
	
	@Autowired private RecipeService recipeService;
	
	/**
	 * MÉTODO que gestiona las peticiones GET a /admin/recipes y que devuelve una lista de recetas
	 * @param isPending puede ser true o false:		
	 * @return 	
	 * 			- si requestParam=false --> devuelve todas las recetas que ya han sido aprobadas por el administrador
	 * 			- si requestParam=true --> devuelve todas las recetas que NO han sido aprobadas por el administrador
	 */
	@GetMapping("/admin/recipes")
	public ResponseEntity<List<Recipe>> findAllRecipes(@RequestParam(required = false) boolean isPending){
		
		ResponseEntity<List<Recipe>> re = null ;
		
		if(isPending) {
			if(this.recipeService.findAllRecipesPending().isEmpty()) {
				re = ResponseEntity.noContent().build();
			}else {
				re = ResponseEntity.ok(this.recipeService.findAllRecipesPending());
			}
		}else {
			if(this.recipeService.findAllRecipesNotPending().isEmpty()) {
				re = ResponseEntity.noContent().build();
			}else {
				re = ResponseEntity.ok(this.recipeService.findAllRecipesNotPending());
			}
		}
		

		return re;
	
	}


	
	/**
	 * MÉTODO que gestiona petición GET para cambiar el estado de una receta isPending
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/recipes/{id}")
	public ResponseEntity<Recipe> changeStatusRecipe(@PathVariable Integer id){
		Recipe recipe = this.recipeService.findRecipeById(id);
		
		if(recipe==null) {
			throw new RecipeNotFoundException(id);
		}
		else if(!recipe.getIsPending()){
			throw new RecipeStatusException(id);
		}else {
			recipe.setIsPending(false);
			this.recipeService.addRecipeBBDD(recipe);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
	}
	
	
	
	//GESTIÓN DE EXCEPCIONES
	
	
	/**
	 * GESTIÓN DE EXCEPCIÓN RecipeNotFoundException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si la receta no se encuentra 
	 */
	@ExceptionHandler(RecipeNotFoundException.class)
	public ResponseEntity<ApiError> handleRecipeNotFound(RecipeNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * GESTIÓN DE EXCEPCIÓN RecipeStatusException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si la receta ya está aprobaba por el administrador
	 */
	@ExceptionHandler(RecipeStatusException.class)
	public ResponseEntity<ApiError> handleRecipeStatus(RecipeStatusException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * GESTIÓN DE EXCEPCIÓN DE JSON MAL FORMADO
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción --> ignora la traza de la excepción
	 */
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.BAD_REQUEST);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	

}
