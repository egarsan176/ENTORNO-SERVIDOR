package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity

public class Category {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@JsonIgnore
	@Transient
	private Integer hola; //campo que no necesita ser mapeados a la base de datos
	
	public Category() {
		super();
	} 
	
	/**
	 * Este constructor es una solución temporal porque en angular me está transformado el id de la categoría a String y entonces cuando llega al constructor de  de categoría no funciona
	 * @param name
	 */
	public Category(String name) {
		super();
		this.id = Integer.parseInt(name);
	}
	
	public Category(String name, Integer hola) {
		super();
		this.name = name;
	}


	public Category(Integer id) {
		super();
		this.id = id;
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
		return "Category [id=" + id + ", name=" + name + "]";
	}

	public Integer getHola() {
		return hola;
	}

	public void setHola(Integer hola) {
		this.hola = hola;
	}

	
	
	
	
	

}
