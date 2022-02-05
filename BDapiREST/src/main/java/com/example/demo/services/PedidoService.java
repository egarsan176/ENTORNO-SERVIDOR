package com.example.demo.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.DatosPedido;
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
	 *USO DE @TRANSACTIONAL
	 * La persistencia y eliminación de objetos requiere una transacción en JPA.
	 * Con JPA/Hibernate cada vez que deseemos hacer una modificación sobre la base de datos necesitamos una transacción activa.
	 * Para asegurarnos de que se esté ejecutando una transacción, lo que hacemos es anotar el método con @Transactional.
	 */
	
	/**
	 * AÑADIR UN NUEVO PEDIDO AL REPOSITORIO
	 * @param pedido
	 * @return pedido que se ha añadido
	 */
	@Transactional
	public Pedido addPedidoaLaBBDD(Pedido pedido) {
		return this.pedidoREPO.save(pedido);
		}
	
	/**
	 * ELIMINAR UN PEDIDO
	 * @param pedido que se quiere eliminar
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
	 * ENCONTRAR LA LISTA DE PEDIDOS DE UN USUARIO EN CONCRETO
	 * @param el id del usuario
	 * @return lista de pedidos del usuario en concreto
	 */
	public List<Pedido>findListaPedidosUser(Long id){
		return pedidoREPO.findByUsuarioId(id);
	}
	
	/**
	 * BUSCAR UN PEDIDO A TRAVÉS DE SU ID
	 * @param pedidoID
	 * @return pedido que coincide con ese ID
	 */
	public Pedido findPedido(Integer pedidoID) {
		return this.pedidoREPO.findById(pedidoID).orElse(null);
	}
	
	/**
	 * PARA CALCULAR EL PRECIO TOTAL DE UN PEDIDO
	 * @param pedido del que quiero saber su coste
	 * @return double precio del pedido
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
	 * AÑADIR UNA NUEVA LÍNEA DE PEDIDO 
	 * @param pedido al que quiero añadir la nueva línea
	 * @param produId el id del producto que quiero añadir a la línea
	 * @param cantidad la cantidad que quiero añadir a la línea de ese producto
	 * @return la nueva línea de pedido que se ha añadido
	 */
	public LineaPedido  addLineaPedido(Pedido pedido, Integer produId, Integer cantidad) {

		Producto produ = this.productoREPO.findById(produId).orElse(null); //selecciono el producto en concreto a través de su ID
		
		LineaPedido lineaDePedidosNueva = new LineaPedido(pedido, produ); //creamos una copia de la linea del usuario para comprobar si la tiene o no
		
		//si el pedido contiene esa línea, se modifica la cantidad, sumandolas
		
		//compruebo si en el array de lineas de pedidos del pedido se encuentra esa linea
		if(pedido.getListadoLineasPedido().contains(lineaDePedidosNueva)) {
			
			//recupero el indice de la linea de Pedido del pedido de ese usuario
			int posicionLineaPedido = pedido.getListadoLineasPedido().indexOf(lineaDePedidosNueva);
			
			//guardo la cantidad antigua de la linea de pedido
			int cantidadAntigua = pedido.getListadoLineasPedido().get(posicionLineaPedido).getCantidad();
			
			//guardo la nueva cantidad de la línea
			int nuevaCantidad = cantidadAntigua + cantidad;
			
			//linea del pedido que yo quiero modificar
			//seteo la cantidad con la suma de la cantidad antigua de la línea más la que le paso por parámetro
			pedido.getListadoLineasPedido().get(posicionLineaPedido).setCantidad(nuevaCantidad);
			
			
		}	else {
			
			lineaDePedidosNueva.setCantidad(cantidad);
			pedido.getListadoLineasPedido().add(lineaDePedidosNueva);
			this.lineaREPO.save(lineaDePedidosNueva);
			
		}
		return lineaDePedidosNueva;
	}
	
	
	/**
	 * ESTABLECER UNOS DATOS INICIALES EN UN PEDIDO
	 * Este método establece que por defecto los datos de un pedido sean los del usuario al que pertenece el pedido
	 * @param pedido 
	 * @param usuario 
	 */
	public void establecerDatosInicialesPedido(Pedido pedido, Usuario usuario) {
		pedido.setDireccion(usuario.getDireccion());
		pedido.setEmail(usuario.getEmail());
		pedido.setTelefono(usuario.getTelefono());
	}

	/**
	 * EDITAR LOS DATOS DE UN PEDIDO
	 * @param id
	 * @param email
	 * @param telefono
	 * @param direccion
	 * @param listaDeCantidades
	 * @param envio
	 * @param usuario
	 * @return pedido 	que ha sido editado
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
				eliminarLinea(pedido, null);
			}
		}
		this.pedidoREPO.save(pedido);
		return pedido;
		
		
	}

	/**
	 * ENCUENTRA EL PEDIDO DE UN USUARIO QUE NO TIENE ASOCIADA NINGUNA LÍNEA DE PEDIDO
	 * @param idUSuario 
	 * @return pedido que no tiene lineas asociadas
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
	 * ELIMINAR UNA LÍNEA DE PEDIDO
	 * @param pedido
	 */
	@Transactional
	public void eliminarLinea(Pedido pedido, LineaPedido linea) {
		
		pedido.removeLinea(pedido.getListadoLineasPedido().indexOf(linea));
		this.pedidoREPO.save(pedido);
		this.lineaREPO.delete(linea);
	}

	/**
	 * EDITA LOS DATOS DE UN PEDIDO (dirección, teléfono, email, envio)
	 * @param pedido
	 * @param datos con la info que se quiere editar en el pedido
	 */
	public void editarDatosPedido(Pedido pedido, DatosPedido datos) {
		
		pedido.setDireccion(datos.getDireccion());
		pedido.setEmail(datos.getEmail());
		pedido.setEnvio(datos.getEnvio());
		pedido.setTelefono(datos.getTelefono());
		
	}
	
	/**
	 * ENCUENTRA LA LISTA DE PEDIDOS DEL REPOSITORIO
	 * @return array de pedidos
	 */
	
	public List<Pedido> findAll() {
		return this.pedidoREPO.findAll();
	}
	

	
	

}
