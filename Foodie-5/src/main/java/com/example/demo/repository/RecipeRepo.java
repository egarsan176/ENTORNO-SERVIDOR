package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Recipe;
/**
 * Encargada de la persistencia de datos de Recipe
 * @author estefgar
 *
 */
public interface RecipeRepo extends JpaRepository<Recipe, Integer>{
	
	public List<Recipe>findByUserId(Long id);
	
	/**
	 * CONSULTA obtener todas las recetas de una categoría que ya han sido aprobadas por el admin
	 * @param id de la categoría a buscar
	 * @return lista de recetas que coinciden con esa categoría y que han sido aprobadas
	 */
	@Query(value="select * from recipe where category_id = ?1 and recipe.is_pending = false", nativeQuery = true) 
	public List<Recipe> findRecipesByCategory(Integer idCategory);
	
	/**
	 * CONSULTA para obtener todas las recetas que se llaman por un nombre en concreto y que ya han
	 * sido aprobadas por el administrador (BUSCADOR DE RECETAS POR NOMBRE)
	 * @param recipeName
	 * @return lista de recetas aprobadas que coinciden exactamente con ese nombre
	 */
	@Query(value="select * from recipe where recipe_name = ?1 and recipe.is_pending=false", nativeQuery = true)
	public List<Recipe> findRecipeByName(String recipeName);
	
	/**
	 * CONSULTA para obtener todas las recetas que se llaman por un nombre en concreto y que ya han
	 * sido aprobadas por el administrador (BUSCADOR DE RECETAS POR NOMBRE)
	 * @param recipeName
	 * @return lista de recetas aprobadas que tienen alguna coincidencia con ese nombre
	 */
	@Query(value="select * from recipe where recipe_name like %?1% and recipe.is_pending=false", nativeQuery = true)
	public List<Recipe> findRecipeBySimilarName(String recipeName);
	
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
	
	/**
	 * CONSULTA que devuelve todas las recetas con estado NO pendiente de la base de datos
	 * @return las recetas que ya han sido aprobadas por el administrador
	 */
	@Query(value="select * from recipe where recipe.is_pending = false", nativeQuery = true)
	public List<Recipe> findRecipesNotPending();
	
	/**
	 * CONSULTA que devuelve todas las recetas con estado SÍ pendiente de la base de datos
	 * @return las recetas que todavía no han sido aprobadas por el administrador
	 */
	@Query(value="select * from recipe where recipe.is_pending = true", nativeQuery = true)
	public List<Recipe> findRecipesPending();
	

	@Query(value= "select * from recipe r, recipe_ingredient_line i, ingredient_line il\n"
			+ "where r.id = i.recipe_id and i.ingredient_line_id = il.id and il.ingredient_id IN\n"
			+ "(select id from ingredient where name in ?1)\n"
			+ "GROUP BY r.recipe_name, r.id\n"
			+ "having count(*)  = ?2", nativeQuery=true)
	public List<Recipe> findRecipesFromIngredients(List<String> ingredientsList, int number);
}
