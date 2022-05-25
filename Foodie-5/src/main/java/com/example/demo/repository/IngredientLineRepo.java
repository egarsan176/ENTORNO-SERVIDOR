package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Ingredient;
import com.example.demo.model.IngredientLine;

public interface IngredientLineRepo extends JpaRepository<IngredientLine, Integer> {
	/**
	 * Busca una línea de ingredientes a través de un ingrediente concreto
	 * @param ingredient
	 * @return
	 */
	public IngredientLine findByIngredient(Ingredient ingredient);
	
	/**
	 * Borra de la tabla auxiliar recipe_ingredient_line la línea que coincide con el id que se le pasa por parámetro 
	 * (que actúa como fk y si no la eliminamos no podemos eliminarla del repositorio de líneas)
	 * @param id
	 */
	@Query(value="delete from recipe_ingredient_line where ingredient_line_id = ?1", nativeQuery = true)
	public void deleteIngredientLineFK(Integer id);

}
