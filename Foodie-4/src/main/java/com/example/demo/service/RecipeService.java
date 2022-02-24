package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Category;
import com.example.demo.model.Recipe;
import com.example.demo.model.RecipeDates;
import com.example.demo.repository.RecipeRepo;

@Service
public class RecipeService {
	
	@Autowired private RecipeRepo recipeRepo;
	@Autowired private CategoryService categoryService;
	
	/**
	 *USO DE @TRANSACTIONAL
	 * La persistencia y eliminación de objetos requiere una transacción en JPA.
	 * Con JPA/Hibernate cada vez que deseemos hacer una modificación sobre la base de datos necesitamos una transacción activa.
	 * Para asegurarnos de que se esté ejecutando una transacción, lo que hacemos es anotar el método con @Transactional.
	 */
	
	/**
	 * MÉTODO para añadir una nueva receta a la base de datos
	 * @param recipe
	 * @return receta que se ha añadido
	 */
	@Transactional
	public Recipe addRecipeBBDD(Recipe recipe) {
		return this.recipeRepo.save(recipe);
	}
	
	@Transactional
	public void deleteRecipe(Recipe recipe) {
		this.recipeRepo.delete(recipe);
	}
	/**
	 * MÉTODO para encontrar la lista de recetas que pertenece a un usuario
	 * @param id
	 * @return lista de recetas del usuario
	 */
	public List<Recipe> findRecipeListUser(Long id){
		return this.recipeRepo.findByUserId(id);
	}
	
	/**
	 * MÉTODO para buscar una receta a través de su id
	 * @param id
	 * @return receta que coincide con ese id
	 */
	public Recipe findRecipeById(Integer id) {
		return this.recipeRepo.findById(id).orElse(null);
	}
	
	/**
	 * MÉTODO para devolver todas las recetas del repositorio
	 * @return listado de todas las recetas existentes
	 */
	public List<Recipe> findAllRecipes(){
		return this.recipeRepo.findAll();
	}
	
	public List<Recipe> findAllRecipesByCategory(Integer id){
		return this.recipeRepo.findRecipesByCategory(id);
	}
	
	/**
	 * MÉTODO para editar el nombre y la categoría de una receta
	 * @param recipe
	 * @param datos
	 */
	public void editRecipeDates(Recipe recipe, RecipeDates datos) {
		Integer idCategory = datos.getCategory().getId();
		Category cat = this.categoryService.findById(idCategory);
		datos.setCategory(cat);
		recipe.setCategory(cat);
		recipe.setRecipeName(datos.getRecipeName());
	}


}
