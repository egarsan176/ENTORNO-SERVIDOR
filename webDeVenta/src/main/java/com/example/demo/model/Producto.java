package com.example.demo.model;

import java.util.Objects;

public class Producto {

	//PROPIEDADES
	
	private String id;
	private static int contador = 0;
	private String nombre;
	private double precio;
	private String descripcion;
	
	//CONSTRUCTOR
	
	public Producto() {}
	
	public Producto(String nombre, double precio, String descripcion) {
		super();
		this.id = "p"+contador;
		contador++;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
	}

	//GETTERS Y SETTERS
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
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
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion
				+ "]";
	}

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
	
	
	
	
	
	
	
}
