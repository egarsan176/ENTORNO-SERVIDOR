package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Clase Comment. Entidad.
 * Crea el objeto comentario cuyos atributos son:
 * id, message, user, recipe, isPending, username, recipeName, fecha.
 * @author estefgar
 *
 */
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String message;
	@JsonIgnore
	@ManyToOne
	private User user;
	@JsonIgnore
	@OneToOne
	private Recipe recipe;
	private boolean isPending = true;
	private String username;
	private String recipeName;
	@JsonIgnore
	private Date fecha;
	
	
	public Comment(String message) {
		super();
		this.message = message;
		this.fecha = new Date();
		
	}
	
	public Comment() {
		super();
		this.fecha = new Date();
	}


	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public String getMessage() {
		return message;
	}





	public void setMessage(String message) {
		this.message = message;
	}





	public User getUser() {
		return user;
	}





	public void setUser(User user) {
		this.user = user;
	}





	public Recipe getRecipe() {
		return recipe;
	}





	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}





	public boolean getIsPending() {
		return isPending;
	}





	public void setIsPending(boolean isPending) {
		this.isPending = isPending;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	/**
	 * Este método muestra la fecha en un formato más entendible
	 * @return
	 */
	public String getFechaBonita() {
		return new SimpleDateFormat("dd-MM-yyyy || hh:mm:ss").format(this.fecha);
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", message=" + message + ", user=" + user + ", recipe=" + recipe + ", isPending="
				+ isPending + ", username=" + username + ", fecha=" + fecha + "]";
	}


	
	
	
	

}
