package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Category;
import com.example.demo.model.Ingredient;
import com.example.demo.model.IngredientLine;
import com.example.demo.model.Recipe;
import com.example.demo.model.RecipeDates;
import com.example.demo.model.User;
import com.example.demo.repository.RecipeRepo;

@Service
public class RecipeService {
	
	@Autowired private RecipeRepo recipeRepo;
	@Autowired private CategoryService categoryService;
	@Autowired private IngredientLineService ingredientLineService;
	@Autowired private IngredientService ingredientService;
	@Autowired private UserService userService;
	
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
	
	/**
	 * MÉTODO para borrar una receta de la base de datos
	 * @param recipe
	 */
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
	
	/**
	 * MÉTODO que devuelve todas las recetas de una categoría en concreto
	 * @param id
	 * @return
	 */
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
	
	/**
	 * MÉTODO  para añadirle una nueva línea de ingredientes a una receta ya existente
	 * @param line
	 * @param recipe
	 * @return nueva línea de ingredientes
	 */
	public IngredientLine addIngredientLine(IngredientLine line, Recipe recipe) {
		Ingredient ingredient = line.getIngredient();
		this.ingredientService.addIngredient(ingredient);
		this.ingredientLineService.add(line);
		recipe.getIngredientLine().add(line);
		this.recipeRepo.save(recipe);
		
		return line;
		
	}
	
	/**
	 * MÉTODO para comprobar si una receta ya contiene a un ingrediente concreto a través de una consulta
	 * @param ingredientName
	 * @return 0 si no lo contiene, !=0 si contiene el ingrediente
	 */
	public Integer checkRecipeIngredient(String ingredientName) {
		return this.recipeRepo.isIngredientExists(ingredientName);
	}
	
	/**
	 * MÉTODO para comprobar si ya existe una receta con ese nombre a través de una consulta
	 * @param recipeName
	 * @return 0 si no existe, !=0 si ya existe una receta con ese nombre
	 */
	public Integer checkRecipeName(String recipeName) {
		return this.recipeRepo.isRecipeNameExists(recipeName);
	}
	
	/**
	 * MÉTODO para buscar al usuario de una receta a través de una consulta
	 * @param recipeID
	 * @return el usuario que coincide con el id de una receta
	 */
	public User getUserByRecipeID(Integer recipeID) {
		long idUser =  this.recipeRepo.getUserIDFromRecipe(recipeID);
		return this.userService.findById(idUser);
	}
	
	


}
