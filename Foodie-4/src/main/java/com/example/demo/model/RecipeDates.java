package com.example.demo.model;

public class RecipeDates {
	
	private String recipeName;
	private Category category;
	
	
	public RecipeDates(String recipeName, Category category) {
		super();
		this.recipeName = recipeName;
		this.category = category;
	}


	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}
