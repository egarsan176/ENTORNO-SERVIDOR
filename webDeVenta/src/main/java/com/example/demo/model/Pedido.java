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
	private String envio;
	private String email;
	private String telefono;
	private Map<Producto, Integer> listaDeProductos = new HashMap<>();
	
	//CONSTRUCTOR
	
	public Pedido() {}
	public Pedido(String ref) {
		this.ref= ref;
	}
	
	public Pedido( Map<Producto, Integer> listaDeProductos, String envio) {
		super();
		this.ref = "ref"+contador;
		contador++;
		this.fecha = new Date();
		this.direccion = direccion;
		this.listaDeProductos = listaDeProductos;
		this.envio = envio;
		this.email = email;
		this.telefono = telefono;
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
	
	public String getEnvio() {
		return envio;
	}
	public void setEnvio(String envio) {
		this.envio = envio;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	//HASHCODE, EQUALS, TO STRING
	
	
	

	@Override
	public String toString() {
		return "Pedido [ref=" + ref + ", direccion=" + direccion + ", fecha=" + fecha + ", envio=" + envio + ", email="
				+ email + ", telefono=" + telefono + ", listaDeProductos=" + listaDeProductos + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ref);
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
		return Objects.equals(ref, other.ref);
	}
	public String getFechaBonita() {
		return new SimpleDateFormat("dd-MM-yyyy || hh:mm:ss").format(this.fecha);
	}
	
	
}
