package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Category;
import com.example.demo.model.Recipe;

public interface RecipeRepo extends JpaRepository<Recipe, Integer>{
	
	public List<Recipe>findByUserId(Long id);
	
	/**
	 * CONSULTA obtener todas las recetas de una categoría
	 * @param id de la categoría a buscar
	 * @return lista de recetas que coinciden con esa categoría
	 */
	@Query(value="select * from recipe where category_id = ?1", nativeQuery = true) 
	public List<Recipe> findRecipesByCategory(Integer idCategory);

}
