package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	
	/**
	 * CONSULTA para saber si ya existe en la base de datos alguna receta con el nombre que se le pasa por parámetro
	 * @param recipeName
	 * @return si devuelve 0 es que no existe ninguna receta en la bbdd con ese nombre, si devuelve otro número es que sí existe
	 */
	@Query(value="select count(recipe_name) from recipe where recipe_name = ?1", nativeQuery = true)
	public Integer isRecipeNameExists(String recipeName);
	
	
	/**
	 * CONSULTA para saber si un ingrediente ya existe en una receta
	 * @param ingredientName nombre del ingrediente que quiero comprobar
	 * @return si devuelve 0 es que el ingrediente no existe en la receta, si devuelve otro número es que sí existe
	 */
	@Query(value="select count(*) from recipe r, recipe_ingredient_line ril, ingredient_line il, ingredient i\n"
			+ "where r.id = ril.recipe_id and\n"
			+ "      ril.ingredient_line_id = il.id and\n"
			+ "      il.ingredient_id = i.id and i.name= ?1", nativeQuery = true)
	public Integer isIngredientExists(String ingredientName);
	
	
	/**
	 * CONSULTA para encontrar el id de un usuario a través de una receta
	 * @param idRecipe
	 * @return el id del usuario de esa receta
	 */
	@Query(value="select user_id from recipe where id = ?1", nativeQuery = true)
	public Long getUserIDFromRecipe(Integer idRecipe);
	

}
