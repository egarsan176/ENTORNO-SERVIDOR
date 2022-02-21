package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String recipeName;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Method> method = new  ArrayList<>();
	@JsonIgnore
	@ManyToOne
	private User user;
	@ManyToOne
	private Category category;
	@OneToMany(cascade = CascadeType.ALL)
	private List<IngredientLine> ingredientLine = new ArrayList<>();
	@OneToOne
	private FileDB file;
	
	
	public Recipe(String recipeName, List<Method> method, User user, Category category, List<IngredientLine> ingredientLine) {
		super();
		this.recipeName = recipeName;
		this.method = method;
		this.user = user;
		this.category = category;
		this.ingredientLine = ingredientLine;
	}


	public Recipe(String recipeName, List<Method> method, Category category, List<IngredientLine> ingredientLine) {
		super();
		this.recipeName = recipeName;
		this.method = method;
		this.category = category;
		this.ingredientLine = ingredientLine;
	}
	
	
	
	
	 
}
