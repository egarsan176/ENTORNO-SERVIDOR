package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.model.Notification;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.NotificationRepo;
/**
 * Servicio que se encarga de mediar entre el controller y el repositorio de Notification
 * @author estefgar
 *
 */
@Service
public class NotificationService {
	
	@Autowired private NotificationRepo notificationREPO;
	
	/**
	 * Método para añadir una notificación de Receta Aprobada a la base de datos
	 * @param user
	 * @param recipe
	 * @param title
	 * @return notificación
	 */
	public Notification addNotificationRecipe(User user, Recipe recipe, String title) {
		Notification notif = new Notification();
		notif.setUser(user);
		notif.setTitle(title);
		notif.setMessage("Su receta <"+ recipe.getRecipeName().toUpperCase()+ "> con id "+recipe.getId()+", ha sido aprobada.");
		return this.notificationREPO.save(notif);
		
	}
	
	/**
	 * Método para añadir una notificación de RecetaNo aprobada a la base de datos
	 * @param user
	 * @param recipe
	 * @param title
	 * @return notificación
	 */
	public Notification addNotificationRecipeNotApproved(User user, Recipe recipe, String title) {
		Notification notif = new Notification();
		notif.setUser(user);
		notif.setTitle(title);
		notif.setMessage("Su receta <"+ recipe.getRecipeName().toUpperCase()+ "> con id "+recipe.getId()+", no ha sido aprobada.");
		return this.notificationREPO.save(notif);
		
	}
	
	/**
	 * Método para añadir una notificación de Comentario Aprobado a la base de datos
	 * @param user
	 * @param comment
	 * @param title
	 * @return notificación
	 */
	public Notification addNotificationComment(User user, Comment comment, String title) {
		Notification notif = new Notification();
		notif.setUser(user);
		notif.setTitle(title);
		notif.setMessage("El comentario con id "+comment.getId()+", que publicó en la receta <"+comment.getRecipeName()+"> ha sido aprobado.");
		return this.notificationREPO.save(notif);
		
	}
	
	/**
	 * Método para añadir una notificación de Comentario No aprobado a la base de datos
	 * @param user
	 * @param comment
	 * @param title
	 * @return notificación
	 */
	public Notification addNotificationCommentNotApproved(User user, Comment comment, String title) {
		Notification notif = new Notification();
		notif.setUser(user);
		notif.setTitle(title);
		notif.setMessage("El comentario con id "+comment.getId()+", que publicó en la receta <"+comment.getRecipeName()+"> no cumple las normas y ha sido eliminado.");
		return this.notificationREPO.save(notif);
		
	}
	
	
	
	/**
	 * MÉTODO para eliminar una notificacion de la bbdd
	 * Primero, a través de una consulta, elimina la notification de la tabla autogenerada de user_notifications en la que actúa como fk y luego la elimina del repositorio
	 * @param notificacion a borrar
	 */
	public void delete(Notification notification) {
		this.notificationREPO.deleteNotificationFK(notification.getId());
		this.notificationREPO.delete(notification);
	}
	
	/**
	 * Método para encontrar una notificación a través de su id
	 * @param id
	 * @return notificación que corresponde con ese id
	 */
	public Notification getNotificationByID(Integer id) {
		return this.notificationREPO.findById(id).orElse(null);
	}

	
}
