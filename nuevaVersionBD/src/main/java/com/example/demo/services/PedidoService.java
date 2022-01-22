package com.example.demo.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Pedido addPedidoaLaBBDD(Pedido pedido) {
		return this.pedidoREPO.save(pedido);
		}
	
	/**
	 * Este método borra un pedido concreto del usuario
	 * @param pedido
	 * @param user
	 */
	@Transactional
	public void eliminarPedido(Pedido pedido) {

		//creo un iterador para recorrer las lineas de pedido
		Iterator<LineaPedido> iterator = pedido.getListadoLineasPedido().iterator();
		
		while(iterator.hasNext()) {	//mientras haya siguiente linea
			LineaPedido linea = iterator.next();
			this.lineaREPO.delete(linea);	//elimino la linea del repositorio
		}
		
		this.pedidoREPO.delete(pedido);	//una vez eliminadas todas las lineas, puedo eliminar el pedido del repositorio
		
	}
	
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
		return this.pedidoREPO.findById(pedidoID).orElse(null);
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
			
			
		}	else {
			
			lineaDePedidosNueva.setCantidad(cantidad);
			pedido.getListadoLineasPedido().add(lineaDePedidosNueva);
			this.lineaREPO.save(lineaDePedidosNueva);
			
		}
		
		//this.pedidoREPO.save(pedido);
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
	@Transactional
	public Pedido editarPedido(Integer id, String email, String telefono, String direccion, Integer[] listaDeCantidades,
			String envio, Usuario usuario) {
		
		Pedido pedido = this.pedidoREPO.findById(id).orElse(null);
		
		pedido.setEmail(email);
		pedido.setTelefono(telefono);
		pedido.setDireccion(direccion);
		pedido.setEnvio(envio);

		
		for(int i=0; i<pedido.getListadoLineasPedido().size(); i++){
			if(listaDeCantidades[i]>=0) {
				pedido.getListadoLineasPedido().get(i).setCantidad(listaDeCantidades[i]);
			}else if( listaDeCantidades[i]==0) {
				eliminarLineaVacia(pedido);
			}
		}
		this.pedidoREPO.save(pedido);
		return pedido;
		
		
	}

	/**
	 * Este método localiza los pedidos que no tienen asociada ninguna línea de pedido
	 * @param idUSuario
	 * @return pedido que no tiene lineas
	 */
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

	/**
	 * Este método elimina una línea del pedido que no tenga cantidades en alguna de sus líneas de pedido
	 * @param pedido
	 */
	@Transactional
	public void eliminarLineaVacia(Pedido pedido) {
		//creo un iterador para recorrer las lineas de pedido
		Iterator<LineaPedido> iterator = pedido.getListadoLineasPedido().iterator();
		
		while(iterator.hasNext()) {	//mientras haya siguiente linea
			LineaPedido linea = iterator.next();
			if(linea.getCantidad()==0) {
				this.lineaREPO.delete(linea);	//elimino la linea del repositorio			
			}
		}
	}


	
	

}
