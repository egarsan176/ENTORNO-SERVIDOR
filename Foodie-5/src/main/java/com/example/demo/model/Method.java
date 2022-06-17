package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase Method. Entidad.
 * Crea el objeto Método (pasos de la receta) cuyos atributos son:
 * id, step.
 * @author estefgar
 *
 */
@Entity
public class Method {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String step;

	public Method(String step) {
		super();
		this.step = step;
	}

	public Method() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "Method [id=" + id + ", step=" + step + "]";
	}
	
	
	
	

}
