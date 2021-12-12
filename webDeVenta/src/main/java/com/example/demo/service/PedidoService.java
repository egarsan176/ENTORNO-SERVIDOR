package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class PedidoService {
	
	@Autowired
	private ProductoService productService;
	
	private List<Producto> productos;
	
	private Map<Producto, Integer> pedidoProductoYcantidad = new HashMap<>();
	
	/**
	 * Este método inicializa el mapa de productos y cantidades
	 */
	public void init() {
		
		productos = productService.findAll();
		
		pedidoProductoYcantidad.put(productos.get(0),2);
		pedidoProductoYcantidad.put(productos.get(1),3);
		pedidoProductoYcantidad.put(productos.get(2),1);
		pedidoProductoYcantidad.put(productos.get(3),1);
		
	}
	
	/**
	 * Este método devuelve el mapa de pedidos con las cantidades de cada producto y el producto
	 * @return Hashmap
	 */
	public Map<Producto, Integer> getAll(){
		return this.pedidoProductoYcantidad;
	}
	
	/**
	 * Este método devuelve los pedidos de un usuario en concreto
	 * @param user
	 * @return pedido
	 */
	public List<Pedido>findPedidoUser(Usuario user){
		return user.getPedidos();
	}
	
	/**
	 * Este método actualiza el mapa de cantidades de producto del servicio con las cantidades introducidas por el usuario
	 * @param cantidades
	 */
	public void addProducto(Integer[] cantidades) {
		List<Producto> listaDeProductos = this.productService.findAll();
		Map<Producto, Integer> aux = new HashMap<>();	//creo un mapa auxiliar que luego igualo al mapa del servicio
		
		for(int i = 0; i<cantidades.length; i++) {
			aux.put(listaDeProductos.get(i), cantidades[i]);
		}
		
		this.pedidoProductoYcantidad = aux;
	}
	
	
	public void eliminarProducto() {}
	
	
	
	
	

}
