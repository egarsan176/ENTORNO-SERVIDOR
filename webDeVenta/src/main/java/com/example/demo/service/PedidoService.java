package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class PedidoService {
	@Autowired
	private UsuarioService servicioUser;
	
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

	
	/**
	 * Este método edita los datos de envío de un pedido concreto del usuario
	 * @param ref
	 * @param email
	 * @param telefono
	 * @param direccion
	 * @param usuario
	 */
	public void editarPedido(Integer ref, String email, String telefono, String direccion,Integer[] listaDeCantidades,String envio, Usuario usuario) {
		Pedido pedido = servicioUser.getPedidoByRef(ref, usuario);

		pedido.setDireccion(direccion);
		pedido.setEmail(email);
		pedido.setTelefono(telefono);
		pedido.setEnvio(envio);
		
		Map<Producto, Integer> nuevoMapaProductosYCantidades = new HashMap<>(); //creo un nuevo mapa de producto y cantidades
		List<Producto> listaDeProductos = productService.findAll(); //guardo la lista de productos del servicio
		
		//recorro los valores nuevos de cantidades y voy almacenando en el nuevo mapa los productos con las cantidades
		for(int i=0; i<listaDeCantidades.length; i++) {
			nuevoMapaProductosYCantidades.put(listaDeProductos.get(i), listaDeCantidades[i]);
		}
		
		pedido.setListaDeProductos(nuevoMapaProductosYCantidades);	//modifico el mapa de productos y cantidades
		
	}
	
	/**
	 * Este método calacula el precio total de un pedido
	 * @param pedido
	 * @return double precio
	 */
	public double calcularPrecioTotal(Pedido pedido) {
		double precioTotal = 0;
		
		for(Map.Entry<Producto, Integer> producto : pedido.getListaDeProductos().entrySet() ) {
			
			precioTotal += producto.getKey().getPrecio()*producto.getValue();
		}
		
		return precioTotal;
	}
	
	
	

}
