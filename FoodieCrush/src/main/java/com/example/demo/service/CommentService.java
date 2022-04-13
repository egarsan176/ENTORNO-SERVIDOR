package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.model.Recipe;
import com.example.demo.repository.CommentRepo;

@Service
public class CommentService {
	
	@Autowired private CommentRepo commentREPO;
	
	public Comment addComment(Recipe recipe, Comment comment) {
		
		comment.setRecipe(recipe);
		recipe.getComments().add(comment);
		return this.commentREPO.save(comment);
	}
	
	/**
	 * Este método obtiene todos los comentarios de la BBDD
	 * @return una lista con todos los comentarios de la BBDD
	 */
	public List<Comment> getAllCommentBBDD(){
		return this.commentREPO.findAll();
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
	
	

}
