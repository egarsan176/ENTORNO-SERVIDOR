package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class IngredientLine {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne 
	private Ingredient ingredient;
	private int amount;

}
