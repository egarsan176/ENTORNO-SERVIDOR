package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;
/**
 * Encargada de la persistencia de datos de Category
 * @author estefgar
 *
 */
public interface CategoryRepo extends JpaRepository<Category, Integer>  {
	
	/**
	 * MÉTODO para obtener una categoría por su nombre
	 * @param name
	 * @return categoría si la encuentra, null si no existe
	 */
	public Category findByName(String name);

}
