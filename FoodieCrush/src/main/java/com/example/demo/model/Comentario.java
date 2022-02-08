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
public class Comentario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String textoComentario;
	@ManyToOne  //un usuario puede tener muchos comentarios, un comentario puede tener un único usuario
	private Usuario usuario;

}
