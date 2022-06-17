package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Comment;
/**
 * Encargada de la persistencia de datos de Comments
 * @author estefgar
 *
 */
public interface CommentRepo extends JpaRepository <Comment, Integer>{
	
	/**
	 * CONSULTA que devuelve todos los comentarios con estado NO pendiente de la base de datos
	 * @return los comentarios que ya han sido aprobadas por el administrador
	 */
	@Query(value="select * from comment where comment.is_pending = false", nativeQuery = true)
	public List<Comment> findAllCommentsNotPending();
	
	/**
	 * CONSULTA que devuelve todas los coemntarios con estado SÍ pendiente de la base de datos
	 * @return los coemntarios que todavía no han sido aprobadas por el administrador
	 */
	@Query(value="select * from comment where comment.is_pending = true", nativeQuery = true)
	public List<Comment> findAllCommentsPending();
	
	/**
	 * CONSULTA que devuelve todos los comentarios con estado NO pendiente de una receta en concreto
	 * @return los comentarios de una receta que ya han sido aprobados por el administrador
	 */
	@Query(value="select * from comment where comment.is_pending = false and comment.recipe_id = ?1", nativeQuery = true)
	public List<Comment> findCommentsFromRecipeNotPending(Integer idRecipe);
	
	/**
	 * CONSULTA que devuelve todos los comentarios con estado SÍ pendiente de una receta en concreto
	 * @return los comentarios de una receta que no han sido aprobados por el administrador
	 */
	@Query(value="select * from comment where comment.is_pending = true and comment.recipe_id = ?1", nativeQuery = true)
	public List<Comment> findCommentsFromRecipePending(Integer idRecipe);
	
	
	/**
	 * Borra de la tabla auxiliar recipe_comments el comentario que coincide con el id que se le pasa por parámetro 
	 * (que actúa como fk y si no la eliminamos no podemos eliminarla del repositorio de comentarios)
	 * @param id
	 */
	@Query(value="delete from recipe_comments where comments_id = ?1", nativeQuery = true)
	public void deleteCommentFK(Integer id);

}
