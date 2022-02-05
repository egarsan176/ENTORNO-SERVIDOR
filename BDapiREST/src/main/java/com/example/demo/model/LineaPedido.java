package com.example.demo.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class LineaPedido {


	//PROPIEDADES
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	@JsonBackReference	       // paso3 --> solución del ciclo infinito
	private Pedido pedido;
	
	
	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//CONSTRUCTOR

	public LineaPedido() {}
	
	public LineaPedido(Pedido pedido, Producto producto) {
		super();
		this.pedido = pedido;
		this.producto = producto;
	}
	
	public LineaPedido(Pedido pedido) {
		super();
		this.pedido = pedido;
	}

	public LineaPedido(Pedido pedido, Producto producto, Integer cantidad) {
		super();
		this.pedido = pedido;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	//GETTERS Y SETTERS 
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}


	//HASHCODE Y EQUALS
	
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
		LineaPedido other = (LineaPedido) obj;
		return Objects.equals(id, other.id);
	}
	


	
}
