package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired private CategoryRepo categoryREPO;
	
	/**
	 * MÉTODO para buscar si el nombre de una categoría existe en la base de datos
	 * @param name
	 * @return Categoría si existe, null si no existe
	 */
	public Category findByName(String name) {
		return this.categoryREPO.findByName(name);
	}
	
	/**
	 * MÉTODO para buscar una categoría a través de su id
	 * @param id
	 * @return Categoría si existe, null si no existe
	 */
	public Category findById(Integer id) {
		return this.categoryREPO.findById(id).orElse(null);
	}

}
