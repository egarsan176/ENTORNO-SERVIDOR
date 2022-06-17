package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Clase IngredientLine. Entidad.
 * Crea el objeto Línea de Ingredientes, cuyos atributos son:
 * id, ingredient, cantidad
 * @author estefgar
 *
 */
@Entity
public class IngredientLine {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL) 
	private Ingredient ingredient;
	private int amount;
	
	
	public IngredientLine(Ingredient ingredient, int amount) {
		super();
		this.ingredient = ingredient;
		this.amount = amount;
	}


	public IngredientLine() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Ingredient getIngredient() {
		return ingredient;
	}


	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "IngredientLine [id=" + id + ", ingredient=" + ingredient + ", amount=" + amount + "]";
	}
	
	
	
	

}
