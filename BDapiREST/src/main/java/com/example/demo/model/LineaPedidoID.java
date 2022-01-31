package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

public class LineaPedidoID implements Serializable{

	private Integer pedido;
	private Integer producto;
	
	public LineaPedidoID() {}

	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

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
		LineaPedidoID other = (LineaPedidoID) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(producto, other.producto);
	}
}
