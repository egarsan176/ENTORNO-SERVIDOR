package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IngredientLineRepo;

@Service
public class IngredientLineService {
	
	@Autowired private IngredientLineRepo  ingredientLineRepo;
	
	

}
