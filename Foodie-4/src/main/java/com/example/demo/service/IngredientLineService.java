package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Ingredient;
import com.example.demo.model.IngredientLine;
import com.example.demo.repository.IngredientLineRepo;


@Service
public class IngredientLineService {
	
	@Autowired
	private IngredientLineRepo ingredientLineREPO;
	
	@Transactional
	public IngredientLine add(IngredientLine ingredientLine) {
		return this.ingredientLineREPO.save(ingredientLine);
	}
	
	public List<IngredientLine> findAll(){
		return this.ingredientLineREPO.findAll();
	}
	
	public IngredientLine findById(Integer id) {
		return this.ingredientLineREPO.findById(id).orElse(null);
	}
	
	public IngredientLine edit(IngredientLine ingredientLine, Integer amount) {
		ingredientLine.setAmount(amount);
		return this.ingredientLineREPO.save(ingredientLine);
	}
	
	public void delete(IngredientLine ingredientLine) {
		this.ingredientLineREPO.delete(ingredientLine);
	}
	
	public IngredientLine findByIngredient(Ingredient ingredient) {
		return this.ingredientLineREPO.findByIngredient(ingredient);
	}
	
	
	
	
	
	
	
	

}
