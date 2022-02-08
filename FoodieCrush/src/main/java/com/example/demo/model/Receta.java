package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Receta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;
	private List<String> pasos = new  ArrayList<>();
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Categoria categoria;
	@OneToMany
	private LineaIngrediente lineaIngredientes;
	@OneToMany
	private List<Comentario> listaComentarios = new  ArrayList<>();
	
}
