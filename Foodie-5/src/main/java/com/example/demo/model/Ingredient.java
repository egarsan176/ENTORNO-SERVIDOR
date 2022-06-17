package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Clase Ingredient. Entidad.
 * Crea el objeto ingrediente, cuyos atributos son:
 * id, name, isPending.
 * @author estefgar
 *
 */
@Entity
public class Ingredient {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private boolean isPending;
	
	public Ingredient(String name) {
		super();
		this.name = name;
	}

	public Ingredient() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + "]";
	}

	public boolean isPending() {
		return isPending;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}


	
	
	
	

}
