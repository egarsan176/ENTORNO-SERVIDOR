package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {
	
	//PROPIEDADES

	@Id
	private Integer id;
	private static int contador = 0;
		
	@Column(name = "nombre")
	private String nombre;
		
	@Column(name = "precio")
	private double precio;
		
	@Column(name = "descripcion")
	private String descripcion;
	
	//CONSTRUCTOR
	
	public Producto() {
		this.id = contador;
		incrementarContador();
	}
		
	public Producto(String nombre, double precio, String descripcion) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.id = contador;
		incrementarContador();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//HASHCODE, EQUALS, TO STRING
	
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Producto other = (Producto) obj;
			return Objects.equals(id, other.id);
		}

		@Override
		public String toString() {
			return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion
					+ "]";
		}
		
		/**
		 * Este método aumenta el contador
		 */
		public void incrementarContador() {
			this.contador++;
		}

}
