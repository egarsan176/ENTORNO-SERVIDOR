package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.internal.util.type.PrimitiveWrapperHelper.IntegerDescriptor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Category {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer hola;
	
	/**
	 * Este constructor es una solución temporal porque en angular me está transformado el id de la categoría a String y entonces cuando llega al constructor de 
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
	
	
	
	

}
