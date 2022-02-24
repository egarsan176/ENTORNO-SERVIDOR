package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.IngredienteLineNotFoundException;
import com.example.demo.exception.RecipeNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.IngredientLine;
import com.example.demo.model.Recipe;
import com.example.demo.model.RecipeDates;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.RecipeService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class RecipeController {
	
	/**
	 * PARA TODAS LAS PETICIONES A /RECIPES NECESITO TENER EL TOKEN
	 */
	
	@Autowired private RecipeService recipeService;
	@Autowired private CategoryService categoryService;
	@Autowired private UserService userService;
	
	//ACCESO A RECURSOS DE PRIMER NIVEL
	
	/**
	 * MÉTODO que gestiona las peticiones GET a /recetas y que devuelven una lista.
	 * Busca todas las recetas del repositorio, todas las recetas por una categoría o todas las recetas de un usuario.
	 * RequestParam --> http://localhost:9000/recipes?userID=1   --- http://localhost:9000/recipes?categoryID=1
	 * 
	 * @return 	si no tiene @requestParam, la lista de recetas del repo
	 * 			si tiene @requestParam:
			 * 		- usuario no existe --> exception UserNotFoundException
			 * 		- usuario existe y tiene recetas --> devuelve las recetas del usuario sin mostrar su info 200 OK
			 * 		- usuario existe y no tiene recetas --> 204 No Content
			 * 		- si la categoría existe --> lista de recetas que coinciden con esa categoría
			 * 		- si la categoría existe y no tiene recetas --> 204 no content
			 * 		- si la categoría no existe --> excepción
	 */

	@GetMapping("/recipes")
	public ResponseEntity<List<Recipe>> findRecipes(@RequestParam(required = false) Long userID, @RequestParam(required = false) Integer categoryID){
		
		List<Recipe> recipes = this.recipeService.findAllRecipes();
		ResponseEntity<List<Recipe>> re = null ;
		
		if(userID==null && categoryID==null && recipes.isEmpty()) {
			re = ResponseEntity.notFound().build(); //debería mandar notFound o noContent ¿?
		}
		else if(userID==null && categoryID==null && !recipes.isEmpty()) {
			re = ResponseEntity.ok(recipes);
		}
		else if(userID!=null && categoryID==null ) {
			
			User user = this.userService.findById(userID);
			if(user==null) {
				throw new UserNotFoundException(userID);
			}
			else if( this.recipeService.findRecipeListUser(userID).isEmpty()){
				re = ResponseEntity.noContent().build();		 
			}else {
				re = ResponseEntity.ok(this.recipeService.findRecipeListUser(userID));
			}
			
		}else if(userID==null && categoryID!=null) {
			Category category = this.categoryService.findById(categoryID);
			if(category==null) {
				throw new CategoryNotFoundException(categoryID);
			}else if(this.recipeService.findAllRecipesByCategory(categoryID).isEmpty()){
				re = ResponseEntity.noContent().build();
			}else {
				re = ResponseEntity.ok(this.recipeService.findAllRecipesByCategory(categoryID));
			}
		}
		return re;
		 
		
	}
	
	/**
	 * MÉTODO que gestiona peticiones GET a /recipes/id y  busca una receta por su ID
	 * @param recipeID 
	 * @return 
	 * 			si existe la receta --> receta
	 * 			si no existe la receta --> exception RecipeNotFoundException
	 */
	@GetMapping("/recipes/{id}")
	public Recipe findRecipeByID(@PathVariable Integer id) {
		if(this.recipeService.findRecipeById(id)!=null) {
			return this.recipeService.findRecipeById(id);	
		}else {
			throw new RecipeNotFoundException(id);
		}
	}
	
	/**
	 * MÉTODO que gestiona petición POST para añadir una receta a la bbdd
	 * @param recipe como body de la petición
	 * @param id del usuario en la url
	 * @return
	 * 			si el usuario no existe --> exception UserNotFoundException()
	 * 			si la categoría de la receta no existe --> exception CategoryNotFoundException()
	 * 			si todo es correcto --> receta
	 */
	@PostMapping("/recipes/{id}")
	public Recipe addRecipe(@RequestBody Recipe recipe, @PathVariable Long id) {
		User user = this.userService.findById(id);
		if(user!=null) {
			Integer idCategory = recipe.getCategory().getId();
			Category cat = this.categoryService.findById(idCategory);
			if(cat == null) {
				throw new CategoryNotFoundException(idCategory);
			}else {
				
				recipe.setUser(user);
				recipe.setCategory(cat);
				return this.recipeService.addRecipeBBDD(recipe);
			}
		}else {
			throw new UserNotFoundException(id);
		}

	}
	
	/**
	 * MÉTODO que gestiona una petición DELETE para eliminar una receta de la base de datos
	 * @param id
	 * @return 
	 * 			si la receta no existe --> exception RecipeNotFoundException()
	 * 			si la receta existe --> noContent 
	 */
	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<?> deleteRecipe(@PathVariable Integer id){
		Recipe recipe = this.recipeService.findRecipeById(id);
		
		if(recipe==null) {
			throw new RecipeNotFoundException(id);
		}else {
			this.recipeService.deleteRecipe(recipe);
			return ResponseEntity.noContent().build();
		}
	}
	
	/**
	 * MÉTODO que gestiona petición PUT para editar el nombre y la categoría de una receta existente
	 * @param id
	 * @param datos a modificar de la receta
	 * @return 
	 * 			si la receta no existe --> exception RecipeNotFoundException()
	 * 			si la categoría no existe --> CategoryNotFoundException()
	 * 			si receta y categoría existen --> JSON con los datos que se han editado
	 */
	@PutMapping("recipes/{id}")
	public ResponseEntity<RecipeDates> editRecipe(@PathVariable Integer id, @RequestBody RecipeDates datos){
		
		Recipe recipe = this.recipeService.findRecipeById(id);
		Category category = this.categoryService.findById(datos.getCategory().getId());
		if(recipe == null) {
			throw new RecipeNotFoundException(id);
		}else if(category==null){
			throw new CategoryNotFoundException(datos.getCategory().getId());
		}else {
			this.recipeService.editRecipeDates(recipe, datos);
			this.recipeService.addRecipeBBDD(recipe);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(datos);
		
	}
	
	
	//ACCESO A RECURSOS DE SEGUNDO NIVEL
	
	/**
	 * MÉTODO que gestiona una petición GET para obtener el listado de líneas de ingredientes de una receta
	 * @param id de la receta
	 * @return
	 * 			si la receta no existe --> exception RecipeNotFoundException()
	 * 			si la receta existe y no tiene líneas --> not Found
	 * 			si la receta existe y tiene líneas --> JSON con todas las líneas de pedido de la receta
	 */
	@GetMapping("recipes/{id}/ingredientLine")
	public ResponseEntity<List<IngredientLine>> findAllIngredientsLine(@PathVariable Integer id){
		
		Recipe recipe = this.recipeService.findRecipeById(id);
		if(recipe == null) {
			throw new RecipeNotFoundException(id);
		}else {
			List<IngredientLine> ingredients = this.recipeService.findRecipeById(id).getIngredientLine();
			
			ResponseEntity<List<IngredientLine>> re;
			
			if(ingredients.isEmpty()) {
				re = ResponseEntity.notFound().build(); 		//debería ser noContent??
			}else {
				re = ResponseEntity.ok(ingredients); 
			}
			
			return re;
		}
	}
		
		/**
		 * MÉTODO que gestiona una petición GET para obtener una línea de ingredientes concreta de una receta
		 * @param id de la receta
		 * @return
		 * 			si la receta no existe --> exception RecipeNotFoundException()
		 * 			si la línea no existe --> exception IngredientLineNotFoundException()
		 * 			si la línea existe --> línea
		 */
		@GetMapping("recipes/{id}/ingredientLine/{idLine}")
		public IngredientLine getIngredientLineByID(@PathVariable Integer id, @PathVariable Integer idLine){
			
			Recipe recipe = this.recipeService.findRecipeById(id);
			if(recipe == null) {
				throw new RecipeNotFoundException(id);
			}else {
				IngredientLine ingredientLine = this.recipeService.findRecipeById(id).getIngredientLine().get(idLine);
				
				if(ingredientLine == null) {
					throw new IngredienteLineNotFoundException(idLine);
				}else {
					return ingredientLine;
				}

			}

		
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
	 * GESTIÓN DE EXCEPCIÓN UserNotFoundException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si el usuario no se encuentra
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * GESTIÓN DE EXCEPCIÓN CategorynotFoundException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si la categoría no se encuentra 
	 */
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ApiError> handleCategoryNotFound(CategoryNotFoundException ex) {
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
