package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pedido {
	
	//PROPIEDADES
	
	private String ref;
	private static int contador = 0;
	private String direccion;
	private Date fecha;
	private Map<Producto, Integer> listaDeProductos = new HashMap<>();
	
	//CONSTRUCTOR
	
	public Pedido() {}
	public Pedido(String ref) {
		this.ref= ref;
	}
	
	public Pedido(String direccion, Map<Producto, Integer> listaDeProductos) {
		super();
		this.ref = "ref"+contador;
		contador++;
		this.fecha = new Date();
		this.direccion = direccion;
		this.listaDeProductos = listaDeProductos;
	}

	//GETTERS Y SETTERS
	
	public String getRef() {
		return ref;
	}


	public void setRef(String ref) {
		this.ref = ref;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Map<Producto, Integer> getListaDeProductos() {
		return listaDeProductos;
	}


	public void setListaDeProductos(Map<Producto, Integer> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	//HASHCODE, EQUALS, TO STRING
	@Override
	public int hashCode() {
		return Objects.hash(direccion, fecha, listaDeProductos, ref);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(direccion, other.direccion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(listaDeProductos, other.listaDeProductos) && Objects.equals(ref, other.ref);
	}


	@Override
	public String toString() {
		return "Pedido [ref=" + ref +", direccion=" + direccion + ", fecha=" + fecha
				+ ", listaDeProductos=" + listaDeProductos + "]";
	}


	public String getFechaBonita() {
		return new SimpleDateFormat("dd-MM-yyyy || hh:mm:ss").format(this.fecha);
	}
	
	
	
}
