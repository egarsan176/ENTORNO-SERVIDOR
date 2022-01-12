package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

//v2
@Service
public class PedidoService {
	
	//inyecto los servicios para poder usarlos
	@Autowired
	private UsuarioService servicioUser;
	
	@Autowired
	private ProductoService productService;
	
	//private List<Producto> productos;
	
	//private Map<Producto, Integer> pedidoProductoYcantidad = new HashMap<>();

	
//	/**
//	 * Este método inicializa el mapa de productos y cantidades.
//	 * Ahora no está en uso porque era de prueba
//	 */
//	public void init() {
//		
//		productos = productService.findAll();
//		
//		pedidoProductoYcantidad.put(productos.get(0),2);
//		pedidoProductoYcantidad.put(productos.get(1),3);
//		pedidoProductoYcantidad.put(productos.get(2),1);
//		pedidoProductoYcantidad.put(productos.get(3),1);
//		
//	}
	
	/**
	 * Este método devuelve la lista de productos
	 * @return List <Producto>
	 */
	public List<Producto> getAll(){
		return this.productService.findAll();
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
	 * Este método actualiza el las cantidades de cada producto
	 * @param cantidades
	 */
	public void addProducto(Integer[] cantidades) {
		List<Producto> listaDeProductos = this.productService.findAll();
		int i = 0;
		
		for(Producto prod : listaDeProductos) {
			prod.setCantidad(cantidades[i]);
			i++;
		}
		
	}

	
	/**
	 * Este método edita los datos de un pedido concreto del usuario
	 * @param ref
	 * @param email
	 * @param telefono
	 * @param direccion
	 * @param usuario
	 */
	public void editarPedido(Integer ref, String email, String telefono, String direccion,Integer[] listaDeCantidades,String envio, Usuario usuario) {
		Pedido pedido = servicioUser.getPedidoByRef(ref, usuario); //guardo el pedido

		pedido.setDireccion(direccion);
		pedido.setEmail(email);
		pedido.setTelefono(telefono);
		pedido.setEnvio(envio);
		
		List<Producto> listaDeProductos = productService.findAll(); //guardo la lista de productos del servicio
		List<Producto> nuevaLista = new ArrayList<>();
		
		double total = 0;
		
		//recorro la lista de productos
		//recorro los valores nuevos de cantidades y voy almacenando en la nueva lista las cantidades
		//almaceno en total el precio de los productos por sus cantidades
		for(int i=0; i<listaDeCantidades.length; i++) {
			total+=listaDeProductos.get(i).getPrecio()*listaDeCantidades[i];	
			nuevaLista.add(listaDeProductos.get(i));
			nuevaLista.get(i).setCantidad(listaDeCantidades[i]);
		}
		pedido.setCosteTotalPedido(total);
		pedido.setListaDeProductos(nuevaLista);	//modifico la lista de productos y cantidades
	}
	
	/**
	 * Este método calcula el precio total de un pedido
	 * @param pedido
	 * @return double precio
	 */
	public double calcularPrecioTotal(Pedido pedido) {
		double precioTotal = 0;
//		//uso un forEach para iterar el mapa
//		for(Map.Entry<Producto, Integer> producto : this.pedidoProductoYcantidad.entrySet() ) {
//			
//			precioTotal += producto.getKey().getPrecio()*producto.getValue();
//		}
		for( Producto producto: productService.findAll()) {
			
			precioTotal+=producto.getPrecio()*producto.getCantidad();
		}
		
		return precioTotal;
	}
}
