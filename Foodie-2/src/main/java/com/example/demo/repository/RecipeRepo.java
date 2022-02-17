package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Recipe;

public interface RecipeRepo extends JpaRepository<Recipe, Integer>{
	
	public List<Recipe>findByUserId(Long id);

}
