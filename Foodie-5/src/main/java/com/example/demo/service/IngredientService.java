package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Ingredient;
import com.example.demo.repository.IngredientRepo;

@Service
public class IngredientService {
	
	@Autowired private IngredientRepo ingredientREPO;
	/**
	 * MÉTODO para añadir un ingrediente a la bbdd
	 * @param ingredient
	 * @return el ingrediente que se ha añadido
	 */
	@Transactional
	public void addIngredient(Ingredient ingredient) {
		
		this.ingredientREPO.save(ingredient);
		
	}
	
	/**
	 * MÉTODO para conseguir el listado de ingredientes de la base de datos
	 * @return lista de ingredientes almacenados en la base de datos
	 */
	public List<Ingredient> getAllIngredientsFromBD(){
		return this.ingredientREPO.findAll();
	}
	
	/**
	 * MÉTODO para conseguir los nombres de los ingredientes de la base de datos
	 * @return una lista con los nombres de los ingredientes de la base de datos
	 */
	public List<String> getNameAllIngredientsFromBD(){
		return this.ingredientREPO.getNameAllIngredients();
	}
	
	public void updateIngredient(String name) {
		this.ingredientREPO.updateIngredient(name);
	}
	
	/**
	 * MÉTODO para obtener los ingredientes con estado is_pending=false
	 * @return lista con los ingredientes aprobados de la base de datos
	 */
	public List<Ingredient> getIngredientsApproved(){
		return this.ingredientREPO.getIngredientsApproved();
	}
	
	/**
	 * MÉTODO para obtener los ingredientes con estado is_pending=true
	 * @return lista con los ingredientes no aprobados de la base de datos
	 */
	public List<Ingredient> getIngredientsPending(){
		return this.ingredientREPO.getIngredientsPending();
	}


}
