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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.exception.CommentStatusException;
import com.example.demo.exception.IngredientNotFoundException;
import com.example.demo.exception.IngredientStatusException;
import com.example.demo.exception.RecipeNotFoundException;
import com.example.demo.exception.RecipeStatusException;
import com.example.demo.exception.UserDeleteException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Notification;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.IngredientService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.RecipeService;
import com.example.demo.service.UserService;
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
	@Autowired private CommentService commentService;
	@Autowired private IngredientService ingredientService;
	@Autowired private NotificationService notificationService;
	@Autowired private UserService userService;
	
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
	 * @return receta
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
			User userRecipe = recipe.getUser();
			String titleNotif = "Receta Aprobada";
			Notification notif = this.notificationService.addNotificationRecipe(userRecipe, recipe, titleNotif);
			recipe.setIsPending(false);
			this.recipeService.addRecipeBBDD(recipe);
			userRecipe.getNotifications().add(notif);
			this.userService.addUser(userRecipe);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
	}
	
	/**
	 * MÉTODO que gestiona las peticiones GET a /admin/recipes/comments y que devuelve una lista de comentarios
	 * @param isPending puede ser true o false:		
	 * @return 	
	 * 			- si requestParam=false --> devuelve todas los comentarios que ya han sido aprobados por el administrador
	 * 			- si requestParam=true --> devuelve todas los comentarios que NO han sido aprobados por el administrador
	 */
	@GetMapping("/admin/recipes/comments")
	public ResponseEntity<List<Comment>> findAllComments(@RequestParam(required = false) boolean isPending){
		
		ResponseEntity<List<Comment>> re = null ;
		
		if(isPending) {
			if(this.commentService.getAllCommentBDPending().isEmpty()) {
				re = ResponseEntity.noContent().build();
			}else {
				re = ResponseEntity.ok(commentService.getAllCommentBDPending());
			}
		}else {
			if(this.commentService.getAllCommentBDnotPending().isEmpty()) {
				re = ResponseEntity.noContent().build();
			}else {
				re = ResponseEntity.ok(this.commentService.getAllCommentBDnotPending());
			}
		}
		

		return re;
	
	}
	
	/**
	 * MÉTODO que gestiona petición GET para cambiar el estado de un comentario isPending
	 * @param id
	 * @return comentario
	 */
	@GetMapping("admin/recipes/comments/{commentID}")
	public ResponseEntity<Comment> changeStatusCommentFromRecipe(@PathVariable Integer commentID){
		
			Comment comment = this.commentService.getCommentFromRecipeByID(commentID);
			if(comment==null) {
				throw new CommentNotFoundException(commentID);
			}else if (!comment.getIsPending()){
				throw new CommentStatusException(commentID);
			}else {
				User userComment = comment.getUser();
				String titleNotif = "Comentario Aprobado";
				Notification notif = this.notificationService.addNotificationComment(userComment, comment, titleNotif);
				comment.setIsPending(false);
				this.commentService.addComment(comment);
				userComment.getNotifications().add(notif);
				this.userService.addUser(userComment);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(comment);
		}
	/**
	 * MÉTODO que gestiona peticiones GET a /admin/users para obtener la lista de usuarios registrados en la base de datos
	 * @return lista de usuarios
	 */
	@GetMapping("admin/users")
	public ResponseEntity<List<User>> getAllUsersFromBD(){
		List<User> users = this.userService.findAllUsers();
		ResponseEntity<List<User>> re = null ;
		
		if(users.isEmpty()) {
			re = ResponseEntity.noContent().build();
		}else {
			re =  ResponseEntity.ok(users);
		}
		
		return re;
	}
	
	/**
	 * MÉTODO que gestiona peticiones DELETE a admin/users/id para eliminar a un usuario de la base de datos
	 */
	@DeleteMapping("admin/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		
		User userDel = this.userService.findById(id);
		
		if(userDel == null) {
			throw new UserNotFoundException(id);
		}
		if("ADMIN".equals(userDel.getRole())) {
			throw new UserDeleteException(id);
		}
		
		this.recipeService.deleteAllRecipesUser(userDel);
		this.userService.deleteUser(userDel);
		return ResponseEntity.noContent().build();
		
	}
	
	
	/**
	 * MÉTODO que a través de una petición GET obtiene los ingredientes de la base de datos sin mostrar repetidos
	 * @param isPending 
	 * @return 	isPending es true --> devuelve los ingredientes pendientes
	 * 			isPending es false --> devuelve los ingredientes aprobados
	 * 			no contiene isPending --> devuelve todos los ingredientes de la BBDD
	 */
	@GetMapping("admin/ingredients")
	public ResponseEntity<List<Ingredient>> getIngredients(@RequestParam(required = false) String isPending){
		
		
		ResponseEntity<List<Ingredient>> re = null;
		
		if(isPending == null) {
			List<Ingredient> ingredients = this.ingredientService.getAllIngredientsFromBDWithoutRep();
			if(ingredients.isEmpty()) {
				re = ResponseEntity.noContent().build();
			}else {
				re = ResponseEntity.ok(ingredients);
			}
		}
		else {
			if("false".equals(isPending)) {
				List<Ingredient> ingredients = this.ingredientService.getIngredientsApproved();
				if(ingredients.isEmpty()) {
					re = ResponseEntity.noContent().build();
				}else {
					re = ResponseEntity.ok(ingredients);
				}
			}else if("true".equals(isPending)) {
				List<Ingredient> ingredients = this.ingredientService.getIngredientsPending();
				if(ingredients.isEmpty()) {
					re = ResponseEntity.noContent().build();
				}else {
					re = ResponseEntity.ok(ingredients);
				}
			}
		}
		
		
		
		return re;
		}
	
	/**
	 * MÉTODO que hace una petición GET a admin/ingredients/id para cambiar el estado pendiente de un ingrediente
	 * @param id
	 * @return el ingrediente con el estado cambiado
	 */
	@GetMapping("admin/ingredients/{id}")
	public ResponseEntity<Ingredient> changeStatusIngredient(@PathVariable Integer id){
		
		Ingredient ing = this.ingredientService.getIngredientByID(id);
		
		if(ing == null) {
			throw new IngredientNotFoundException(id);
		}
		else if(!ing.isPending()) {
			throw new IngredientStatusException(id);
		}else {
			ing.setPending(false);
			this.ingredientService.addIngredient(ing);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(ing);
		
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
	 * GESTIÓN DE EXCEPCIÓN IngredientNotFoundException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si el ingrediente no se encuentra 
	 */
	@ExceptionHandler(IngredientNotFoundException.class)
	public ResponseEntity<ApiError> handleIngredientNotFound(IngredientNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * GESTIÓN DE EXCEPCIÓN UserDeleteException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si el usuario a borrar es ADMIN
	 */
	@ExceptionHandler(UserDeleteException.class)
	public ResponseEntity<ApiError> handleUserDeleteException(UserDeleteException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.CONFLICT);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
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
	 * GESTIÓN DE EXCEPCIÓN IngredientStatusException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si el ingrediente ya está aprobaba por el administrador
	 */
	@ExceptionHandler(IngredientStatusException.class)
	public ResponseEntity<ApiError> handleIngredientStatus(IngredientStatusException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * GESTIÓN DE EXCEPCIÓN CommentStatusException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si el comentario ya está aprobaba por el administrador
	 */
	@ExceptionHandler(CommentStatusException.class)
	public ResponseEntity<ApiError> handleCommentStatus(CommentStatusException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.CONFLICT);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
	}
	
	/**
	 * GESTIÓN DE EXCEPCIÓN CommentNotFoundException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si el comentario no se encuentra 
	 */
	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<ApiError> handleCommentNotFound(CommentNotFoundException ex) {
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
