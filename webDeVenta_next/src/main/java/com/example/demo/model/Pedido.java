package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	//PROPIEDADES
	@Id
	private Integer ref;
	private static int contador = 0;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "envio")
	private String envio;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "costeTotalPedido")
	private double costeTotalPedido;
	
	@OneToMany
	private List<Producto> listaDeProductos = new ArrayList<>();
	
	//CONSTRUCTOR
	
	public Pedido() {
		this.ref = contador;
		incrementarContador();
		this.fecha = new Date();
	}
	
	public Pedido(Integer ref) {
		super();
		this.ref= ref;	
	}
	
	public Pedido( List<Producto> listaDeProductos, String envio) {
		super();
		this.ref = contador;
		incrementarContador();
		this.fecha = new Date();
		this.listaDeProductos = listaDeProductos;
		this.envio = envio;
	}
	public Pedido(List<Producto> listaDeProductos) {
		super();
		this.ref = contador;
		incrementarContador();
		this.listaDeProductos = listaDeProductos;
		this.fecha = new Date();
	}
	
	//GETTERS Y SETTERS

	public Integer getRef() {
		return ref;
	}

	public void setRef(Integer ref) {
		this.ref = ref;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public double getCosteTotalPedido() {
		return costeTotalPedido;
	}

	public void setCosteTotalPedido(double costeTotalPedido) {
		this.costeTotalPedido = costeTotalPedido;
	}

	public List<Producto> getListaDeProductos() {
		return listaDeProductos;
	}

	public void setListaDeProductos(List<Producto> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
	}
	
	//HASHCODE, EQUALS, TO STRING
	
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
	
	@Override
	public String toString() {
		return "Pedido [ref=" + ref + ", direccion=" + direccion + ", fecha=" + fecha + ", envio=" + envio + ", email="
				+ email + ", telefono=" + telefono + ", costeTotalPedido=" + costeTotalPedido + ", listaDeProductos="
				+ listaDeProductos + "]";
	}

	/**
	 * Este método aumenta el contador
	 */
	public void incrementarContador() {
		this.contador++;
	}
	
	/**
	 * Este método muestra la fecha en un formato más entendible
	 * @return
	 */
	public String getFechaBonita() {
		return new SimpleDateFormat("dd-MM-yyyy || hh:mm:ss").format(this.fecha);
	}
	
	

}
