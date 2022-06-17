package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepo;
/**
 * Servicio que se encarga de mediar entre el controller y el repositorio de Comment
 * @author estefgar
 *
 */
@Service
public class CommentService {
	
	@Autowired private CommentRepo commentREPO;
	@Autowired private RecipeService recipeService;
	
	/**
	 * Este método guarda un comentario en la base de datos
	 * @param recipe
	 * @param comment
	 * @return comentario guardado
	 */
	public Comment addComment(Comment comment) {
		
		return this.commentREPO.save(comment);
	}
	
	/**
	 * A este método se accede cuando se publica un comentario
	 * @param comment
	 * @param user que publica el comentario
	 * @param recipe asociada al comentario
	 * @return
	 */
	public Comment postComment(Comment comment, User user, Recipe recipe) {
		
		comment.setUser(user);
		comment.setUsername(user.getUsername());
		comment.setRecipe(recipe);
		comment.setRecipeName(recipe.getRecipeName());
		this.addComment(comment);
		recipe.getComments().add(comment);
		this.recipeService.addRecipeBBDD(recipe);
		return comment;
	}
	
	/**
	 * Este método obtiene todos los comentarios de la BBDD
	 * @return una lista con todos los comentarios de la BBDD
	 */
	public List<Comment> getAllCommentBBDD(){
		return this.commentREPO.findAll();
	}
	/**
	 * Este método obtiene todos los comentarios de la base de datos con estado pendiente.
	 * @return lista de comentarios pendientes de aprobación
	 */
	public List<Comment> getAllCommentBDPending(){
		return this.commentREPO.findAllCommentsPending();
	}
	/**
	 * Este método obtiene todos los comentarios de la base de datos con estado no pendiente.
	 * @return lista de comentarios no pendientes
	 */
	public List<Comment> getAllCommentBDnotPending(){
		return this.commentREPO.findAllCommentsNotPending();
	}
	
	/**
	 * Este método obtiene todos los comentarios asociados a una receta (pendientes y no pendientes)
	 * @param recipe de la que se quieren obtener los comentarios
	 * @return lista de comentarios de la receta que se le pasa por parámetro
	 */
	public List<Comment> getCommentsFromRecipe(Recipe recipe){
		return recipe.getComments();
	}
	
	/**
	 * MÉTODO que a través de una consulta, obtiene los comentarios de una receta que ya han sido aprobados por el administrador
	 * @param recipeID
	 * @return lista de comentarios de una receta ya aprobados (is_pending = false)
	 */
	public List<Comment> getCommentsFromRecipeNotPending(Integer recipeID){
		return this.commentREPO.findCommentsFromRecipeNotPending(recipeID);
	}
	
	/**
	 * MÉTODO que a través de una consulta, obtiene los comentarios de una receta que no han sido aprobados por el administrador
	 * @param recipeID
	 * @return lista de comentarios de una receta no aprobados (is_pending = true)
	 */
	public List<Comment> getCommentsFromRecipePending(Integer recipeID){
		return this.commentREPO.findCommentsFromRecipePending(recipeID);
	}
	
	/**
	 * MÉTODO que devuelve un comentario de la base de datos en función del id que se le pasa por parámetro
	 * @param commentID
	 * @return comentaro que coincide con ese id
	 */
	public Comment getCommentFromRecipeByID(Integer commentID) {
		return this.commentREPO.findById(commentID).orElse(null);
	}
	
	/**
	 * Método que busca un comentario en la base de datos a través del id proporcionado
	 * @param id
	 * @return comentario que coincide con el id pasado por parámetro
	 */
	public Comment findCommentById(Integer id) {
		return this.commentREPO.findById(id).orElse(null);
	}
	
	
	/**
	 * MÉTODO para eliminar un comentario de la bbdd
	 * Primero, a través de una consulta, elimina el comentario de la tabla autogenerada de recipe-comments en la que actúa como fk y luego la elimina del repositorio
	 * @param comentario a borrar
	 */
	public void delete(Comment comment) {
		this.commentREPO.deleteCommentFK(comment.getId());
		this.commentREPO.delete(comment);
	}
	
	

}
