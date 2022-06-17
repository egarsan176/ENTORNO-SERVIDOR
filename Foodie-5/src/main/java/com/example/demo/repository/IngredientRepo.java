package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Ingredient;
/**
 * Encargada de la persistencia de datos de Ingredient
 * @author estefgar
 *
 */
public interface IngredientRepo extends JpaRepository<Ingredient, Integer> {
	
	@Query(value="select name from ingredient", nativeQuery = true)
	public List<String> getNameAllIngredients();

	
	@Query(value="select count(name) from ingredient where name = ?", nativeQuery = true)
	public Integer checkIngredientBD(String name);
	
	@Query(value="UPDATE ingredient\n"
			+ "                   SET\n"
			+ "                      is_pending = false\n"
			+ "                   WHERE\n"
			+ "                      name = ?", nativeQuery = true)
	public void updateIngredient(String name);
	
	
	/**
	 * Consulta para obtener los ingredientes que están aprobados en la base de datos
	 */
	@Query(value="select * from ingredient where is_pending = false", nativeQuery=true)
	public List<Ingredient> getIngredientsApproved();
	
	/**
	 * Consulta para obtener los ingredientes que todavía no se han aprobado en la base de datos
	 */
	@Query(value="select * from ingredient where is_pending = true", nativeQuery=true)
	public List<Ingredient> getIngredientsPending();
}


