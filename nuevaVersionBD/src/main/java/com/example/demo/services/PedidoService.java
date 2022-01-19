package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class PedidoService {
	
	@Autowired
	private ProductoRepository productoREPO;
	
	@Autowired
	private PedidoRepository pedidoREPO;
	
	@Autowired
	private LineaPedidoRepository lineaREPO;
	
	@Autowired
	private UsuarioRepository usuarioREPO;
	
	
	/**
	 * Para añadir un pedido a la BBDD
	 * @param pedido
	 * @return
	 */
	public Pedido addPedidoaLaBBDD(Pedido pedido) {
		return this.pedidoREPO.save(pedido);
		}
	
	/**
	 * Este método borra un pedido concreto del usuario
	 * @param pedido
	 * @param user
	 */
	public void eliminarPedido(Pedido pedido) {

		this.pedidoREPO.delete(pedido);
		
	};
	
	/**
	 * Este método devuelve la lista pedidos de un usuario en concreto
	 * @param el id del usuario
	 * @return pedido
	 */
	public List<Pedido>findListaPedidosUser(Long id){
		return pedidoREPO.findByUsuarioId(id);
	}
	
	/**
	 * Este busca un pedido a través de su id
	 * @param pedidoID
	 * @return pedido que coincide con ese ID
	 */
	public Pedido findPedido(Integer pedidoID) {
		return this.pedidoREPO.getById(pedidoID);
	}
	
	/**
	 * Este método calcula el precio total de un pedido
	 * @param pedido
	 * @return double precio
	 */
	public double calcularPrecioTotal(Pedido pedido) {
		double precioTotal = 0;
		
		List<LineaPedido> lineas  = pedido.getListadoLineasPedido();
		
		for( LineaPedido linea: lineas) {
			
			precioTotal+=linea.getProducto().getPrecio()*linea.getCantidad();
		}
		
		return precioTotal;
	}
	
	/**
	 * Este método añade una línea de pedido al pedido que se le pasa por parámetro
	 * @param pedido
	 * @param produId es el id del producto
	 * @param cantidad la cantidad asociada
	 */
	public void  addLineaPedido(Pedido pedido, Integer produId, Integer cantidad) {

		Producto produ = this.productoREPO.findAll().get(produId); //selecciono el producto en concreto a través de su ID
		
		LineaPedido lineaDePedidosNueva = new LineaPedido(pedido, produ); //creamos una copia de la linea del usuario para comprobar si la tiene o no
		
		//si el pedido contiene esa línea, se modifica la cantidad, sumandolas
		
		//compruebo si en el array de lineas de pedidos del pedido se encuentra esa linea
		if(pedido.getListadoLineasPedido().contains(lineaDePedidosNueva)) {
			
			//recupero el indice de la linea de Pedido del pedido de ese usuario
			Integer posicionLineaPedido = pedido.getListadoLineasPedido().indexOf(lineaDePedidosNueva);
			
			//guardo la cantidad antigua de la linea de pedido
			Integer cantidadAntigua = pedido.getListadoLineasPedido().get(posicionLineaPedido).getCantidad();
			
			//linea del pedido que yo quiero modificar
			//seteo la cantidad con la suma de la cantidad antigua de la línea más la que le paso por parámetro
			pedido.getListadoLineasPedido().get(posicionLineaPedido).setCantidad(cantidadAntigua+cantidad);
			
			this.lineaREPO.save(lineaDePedidosNueva);
			
		}	else {
			
			lineaDePedidosNueva.setCantidad(cantidad);
			pedido.getListadoLineasPedido().add(lineaDePedidosNueva);
			this.lineaREPO.save(lineaDePedidosNueva);
			
		}
		
	}
	
	/**
	 * Este método establece por defecto los datos iniciales de un pedido
	 * @param pedido
	 * @param usuario
	 */
	public void establecerDatosInicialesPedido(Pedido pedido, Usuario usuario) {
		pedido.setDireccion(usuario.getDireccion());
		pedido.setEmail(usuario.getEmail());
		pedido.setTelefono(usuario.getTelefono());
	}

	/**
	 * Este método edita los datos de un pedido
	 * @param id
	 * @param email
	 * @param telefono
	 * @param direccion
	 * @param listaDeCantidades
	 * @param envio
	 * @param usuario
	 * @return pedido editado
	 */
	public Pedido editarPedido(Integer id, String email, String telefono, String direccion, Integer[] listaDeCantidades,
			String envio, Usuario usuario) {
		
		Pedido pedido = this.pedidoREPO.getById(id);
		
		pedido.setEmail(email);
		pedido.setTelefono(telefono);
		pedido.setDireccion(direccion);
		pedido.setEnvio(envio);
		
		int i=0;
		for(LineaPedido linea : pedido.getListadoLineasPedido()) {
			if(listaDeCantidades[i]>=0) {
				linea.setCantidad(listaDeCantidades[i]);
				i++;
			}
		}
		return pedido;
		
		
	}

	public Pedido getPedidoSinLineas(Long idUSuario) {
		
		Pedido pedido = new Pedido();
		 
		 //recorro la lista de todos los pedidos del usuario y si existen pedidos vacíos sin lineas asociadas, los borro
		 for (int i = 0; i< this.pedidoREPO.findByUsuarioId(idUSuario).size(); i++) {
			 if(this.pedidoREPO.findByUsuarioId(idUSuario).get(i).getListadoLineasPedido().size() == 0) {
				 pedido = this.pedidoREPO.findByUsuarioId(idUSuario).get(i);
			 }
			 
		 }
		 
		return pedido;
		
		
	}
	
	

}
