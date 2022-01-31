package com.example.demo.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(LineaPedidoID.class)
public class LineaPedido {


	//PROPIEDADES
	@Id
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	@Id
	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
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

	//HASHCODE Y EQUALS
	
	@Override
	public int hashCode() {
		return Objects.hash(pedido, producto);
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
		return Objects.equals(pedido, other.pedido) && Objects.equals(producto, other.producto);
	}

	
}
