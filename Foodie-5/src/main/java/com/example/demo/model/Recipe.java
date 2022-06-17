package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase Recipe. Entidad.
 * Crea el objeto Receta cuyos atributos son:
 * id, recipeName, method, user, category, ingredientLine, file, fecha, isPending, comments.
 * @author estefgar
 *
 */
@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String recipeName;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Method> method = new  ArrayList<>();
	@JsonIgnore
	@ManyToOne (cascade = CascadeType.ALL)
	private User user;
	@ManyToOne
	private Category category;
	@OneToMany(cascade = CascadeType.ALL)
	private List<IngredientLine> ingredientLine = new ArrayList<>();
	
	@OneToOne
	private FileDB file;
	@JsonIgnore
	private Date fecha;
	private boolean isPending = true;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	public Recipe(String recipeName, List<Method> method, User user, Category category, List<IngredientLine> ingredientLine) {
		super();
		this.recipeName = recipeName;
		this.method = method;
		this.user = user;
		this.category = category;
		this.ingredientLine = ingredientLine;
		this.fecha = new Date();
		this.comments = new ArrayList<>();
	}


	public Recipe(String recipeName, List<Method> method, Category category, List<IngredientLine> ingredientLine) {
		super();
		this.recipeName = recipeName;
		this.method = method;
		this.category = category;
		this.ingredientLine = ingredientLine;
		this.fecha = new Date();
		this.comments = new ArrayList<>();
	}


	public Recipe() {
		super();
		this.fecha = new Date();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}


	public List<Method> getMethod() {
		return method;
	}


	public void setMethod(List<Method> method) {
		this.method = method;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public List<IngredientLine> getIngredientLine() {
		return ingredientLine;
	}


	public void setIngredientLine(List<IngredientLine> ingredientLine) {
		this.ingredientLine = ingredientLine;
	}




	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	


	public FileDB getFile() {
		return file;
	}


	public void setFileID(FileDB file) {
		this.file = file;
	}
	
	public boolean getIsPending() {
		return isPending;
	}


	public void setIsPending(boolean isPending) {
		this.isPending = isPending;
	}
	
	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	/**
	 * Este método muestra la fecha en un formato más entendible
	 * @return
	 */
	public String getFechaBonita() {
		return new SimpleDateFormat("dd-MM-yyyy || hh:mm:ss").format(this.fecha);
	}


	@Override
	public String toString() {
		return "Recipe [id=" + id + ", recipeName=" + recipeName + ", method=" + method + ", user=" + user
				+ ", category=" + category + ", ingredientLine=" + ingredientLine + ", file=" + file + ", fecha="
				+ fecha + ", isPending=" + isPending + ", comments=" + comments + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		return Objects.equals(id, other.id);
	}

	






	
	 
}
