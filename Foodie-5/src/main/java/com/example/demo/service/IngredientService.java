package com.example.demo.service;

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
	public Ingredient addIngredient(Ingredient ingredient) {
		return this.ingredientREPO.save(ingredient);
	}

}
