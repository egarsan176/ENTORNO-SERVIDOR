package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.LineaNotFoundException;
import com.example.demo.exception.PedidoNotFoundException;
import com.example.demo.exception.ProductoNotFoundException;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.model.DatosPedido;
import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.services.LineaService;
import com.example.demo.services.PedidoService;
import com.example.demo.services.ProductoService;
import com.example.demo.services.UsuarioService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class MainController {
	
	 
	//INYECCIÓN DE LOS DISTINTOS SERVICIOS
	 @Autowired
	 private UsuarioService servicioUser;
	 
	 @Autowired
	 private PedidoService servicioPedido;
	 
	 @Autowired
	 private ProductoService serviceProducto;
	 
	 @Autowired
	 private LineaService serviceLinea;
	 
	 
	 /////////////////////////////////CONTROLLER API REST
	 
	 /**
		 * SOLO CONSULTA
		 * Para realizar una petición GET a http://localhost:8080/usuario 
		 * @return nos devuelve un JSON con la lista de usuarios de la BBDD
		 */
		@GetMapping("usuario")
		public ResponseEntity<List<Usuario>> findAllUsers() {
			
			List<Usuario> usuarios = this.servicioUser.findAllUsers();
			
			ResponseEntity<List<Usuario>> re; 
			
			if (usuarios.isEmpty()) {
				re = ResponseEntity.notFound().build();
			} else {
				re = ResponseEntity.ok(usuarios);
			}
			return re;
		}
		
		/**
		* SOLO CONSULTA
		* Para realizar una petición GET a http://localhost:8080/usuario/id 
		* @return nos devuelve un JSON con los datos del usuario solicitado
		* @throws UsuarioNotFoundException 
		*/
		@GetMapping("/usuario/{id}")
		public Usuario getUsuarioById(@PathVariable Long id) throws UsuarioNotFoundException {
			
			Usuario user = this.servicioUser.findById(id);

			if (user == null) {
				throw new UsuarioNotFoundException(id);
			} else {
				return user;
			}
		}
		
		/**
		 * GESTIÓN DE EXCEPCIÓN UsuarioNotFoundException
		 * @param ex
		 * @return un json con el estado, fecha, hora y mensaje de la excepción si el usuario no se encuentra --> para ignorar la traza de la excepción
		 */
		@ExceptionHandler(UsuarioNotFoundException.class)
		public ResponseEntity<ApiError> handleUsuarioNoEncontrado(UsuarioNotFoundException ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.NOT_FOUND);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		}
		
		 /**
		 * SOLO CONSULTA
		 * Para realizar una petición GET a http://localhost:8080/producto 
		 * @return nos devuelve un JSON con el catálogo de productos de la BBDD
		 */
		@GetMapping("producto")
		public ResponseEntity<List<Producto>> findAllProducts() {
			
			List<Producto> productos = this.serviceProducto.findAllProducts();
			
			ResponseEntity<List<Producto>> re; //
			
			if (productos.isEmpty()) {
				re = ResponseEntity.notFound().build();
			} else {
				re = ResponseEntity.ok(productos);
			}
			
			return re;
		}
		
		/**
		 * SOLO CONSULTA
		 * Para realizar una petición GET a http://localhost:8080/producto/id 
		 * @return nos devuelve un JSON con la info del producto seleccionado
		 */
		@GetMapping("/producto/{id}")
		public Producto getProductoById(@PathVariable Integer id) {
			
			Producto product = this.serviceProducto.findById(id);
			
			if (product == null) {
				throw new ProductoNotFoundException();
				//si el producto no está me devuelve un 404, not found y me devuelve también toda la traza de la excepción 
			} else {
				return product;
			}
			
		}
		
		/**
		 * SOLO CONSULTA
		 * Para realizar una petición GET a http://localhost:8080/pedido/id 
		 * @return nos devuelve un JSON con la info del pedido seleccionado
		 */
		@GetMapping("/pedido/{id}") 
		public Pedido getPedidoById(@PathVariable Integer id) {
			
			Pedido pedido = this.servicioPedido.findPedido(id);
			
			//modifico el precio del pedido
			 pedido.setCosteTotalPedido(this.servicioPedido.calcularPrecioTotal(pedido));
			
			if (pedido == null) {
				throw new PedidoNotFoundException(id);
			} 
			else {
				return pedido;
			}
		}
		
		/**
		 * GESTIÓN DE EXCEPCIÓN PedidoNotFoundException 
		 * @param ex
		 * @return un json con el estado, fecha, hora y mensaje de la excepción si el pedido no se encuentra --> para ignorar la traza de la excepción
		 */
		@ExceptionHandler(PedidoNotFoundException.class)
		public ResponseEntity<ApiError> handlePedidoNotFound(PedidoNotFoundException ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.NOT_FOUND);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		}
		
		/**
		 * CREAR UN PEDIDO
		 * Hago una petición POST a http://localhost:8080/pedido y en el body le paso el id de un usuario
		 * @param usuario 
		 * @return JSON con los datos del pedido creado
		 */
		@PostMapping("pedido")
		public ResponseEntity<Pedido> addPedido(@RequestBody Usuario usuario) {
			
			Usuario user = this.servicioUser.findById(usuario.getId());
			
			if(user == null) {
				throw new UsuarioNotFoundException(usuario.getId());
			}else {
				
				//creo un nuevo pedido,se lo asocio al usuario y le añado datos iniciales
				Pedido pedido = new Pedido();
				pedido.setUsuario(user);	//he puesto un JSONIgnore en el usuario para que al imprimir el pedido no aparezca toda la info del usuario
				this.servicioPedido.establecerDatosInicialesPedido(pedido, user);
				
				//añado el pedido a la base de datos
				this.servicioPedido.addPedidoaLaBBDD(pedido);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
			}
			
		}
		
		/**
		 * ELIMINAR PEDIDO
		 * Hago una petición DELETE a http://localhost:8080/pedido/id y se elimina el pedido que coincide con el id pasado por parámetro
		 * @param id
		 * @return JSON vacío 
		 */
		@DeleteMapping("pedido/{id}")
		public ResponseEntity<?> deletePedido(@PathVariable Integer id) {
			
			Pedido pedido = this.servicioPedido.findPedido(id);
			
			if (pedido == null) {
				throw new PedidoNotFoundException(id);
			
			}else {
				
				this.servicioPedido.eliminarPedido(pedido);
				return ResponseEntity.noContent().build();
			}
		}
		
		/**
		 * EDITAR UN PEDIDO
		 * Hago una petición PUT a http://localhost:8080/pedido/id y en el body le paso un objeto DatosPedido (contiene los datos que quiero editar del pedido)
		 * @param id
		 * @param datos a modificar del pedido
		 * @return JSON con los datos que se han editado
		 */
		@PutMapping("pedido/{id}")
		public ResponseEntity<DatosPedido> editPedido(@PathVariable Integer id, @RequestBody DatosPedido datos) {
			
			Pedido pedido = this.servicioPedido.findPedido(id);
			
			if(pedido == null) {
				
				throw new PedidoNotFoundException(id);
				
			}else {
				
				this.servicioPedido.editarDatosPedido(pedido, datos);
				this.servicioPedido.addPedidoaLaBBDD(pedido);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(datos);
			}	
			
		}
		
		/**
		 * SOLO CONSULTA
		 * Hago una petición GET a http://localhost:8080/pedido 
		 * @return JSON con todos los pedidos de la base de datos
		 */
		@GetMapping("pedido")
		public ResponseEntity<List<Pedido>> findAllPedidos() {
			
			List<Pedido> pedidos = this.servicioPedido.findAll();
			
			ResponseEntity<List<Pedido>> re; 
			
			if (pedidos.isEmpty()) {
				re = ResponseEntity.notFound().build();
			} else {
				re = ResponseEntity.ok(pedidos);
			}
			
			return re;
		}
		
		/**
		 * SOLO CONSULTA
		 * Hago una petición GET a http://localhost:8080/pedido/{id}/lineaPedido
		 * @return JSON con todas las líneas de un pedido
		 */
		@GetMapping("pedido/{id}/lineaPedido")
		public ResponseEntity<List<LineaPedido>> findAllLineas(@PathVariable Integer id){
			
			List<LineaPedido> lineas =  this.servicioPedido.findPedido(id).getListadoLineasPedido();
			
			ResponseEntity<List<LineaPedido>> re; 
			
			if (lineas.isEmpty()) {
				re = ResponseEntity.notFound().build();
			} 
			else {
				re = ResponseEntity.ok(lineas);
			}
			
			return re;
			
		}
		
		/**
		 * SOLO CONSULTA
		 * Hago una petición GET a http://localhost:8080/pedido/{idPedido}/lineaPedido/{id}
		 * @return JSON con la línea de pedido que coincide con el id pasado en la url
		 */
		@GetMapping("pedido/{idPedido}/lineaPedido/{id}")
		public LineaPedido getLineaById(@PathVariable Integer idPedido, @PathVariable Integer id) {
			
			LineaPedido linea = this.serviceLinea.findById(id);
			
			if (linea == null) {
				throw new LineaNotFoundException(id);
			} 
			else {
				return linea;
			}
		}
		
		/**
		 * PARA CREAR UNA NUEVA LÍNEA DE PEDIDO
		 * Hago una petición POST a http://localhost:8080/pedido/{idPedido}/lineaPedido
		 * @param idPedido lo indico en la URL
		 * @param linea paso en el body el id del pedido, el id del producto y la cantidad
		 * @return JSON con los datos de la nueva linea de pedido
		 */
		@PostMapping("pedido/{idPedido}/lineaPedido")
		public ResponseEntity<LineaPedido> addLineaPedido(@PathVariable Integer idPedido, @RequestBody LineaPedido lineaPedido){
			
			Pedido pedido = this.servicioPedido.findPedido(idPedido);
			Producto product = this.serviceProducto.findById(lineaPedido.getProducto().getId());
			
			if(pedido == null) {
				
				throw new PedidoNotFoundException(idPedido);
				
			}
			else if(!this.serviceProducto.findAllProducts().contains(lineaPedido.getProducto()) || lineaPedido.getProducto() == null) {
				
				throw new ProductoNotFoundException();  //Devuelve 404 con toda la traza y el mensaje de la excepción
				
			}else {
				
				LineaPedido linea = this.servicioPedido.addLineaPedido(pedido, lineaPedido.getProducto().getId(), lineaPedido.getCantidad());
				this.servicioPedido.addPedidoaLaBBDD(pedido);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(linea);
			}
			
		}
			
		/**
		 * PARA EDITAR LA LÍNEA DE PEDIDO (solo la cantidad)
		 * Hago una petición PUT a http://localhost:8080/lineaPedido/id y en el body le paso el id del producto y la cantidad
		 * @param id de la línea que quiero editar
		 * @param lineaPedido con el id del producto y la cantidad
		 * @return JSON con la línea introducida en el body
		 */
		@PutMapping("pedido/{idPedido}/lineaPedido/{id}")
		public LineaPedido editarLineaPedido(@PathVariable Integer id, @RequestBody LineaPedido lineaPedido) {
			
			LineaPedido linea = this.serviceLinea.findById(id);
			
			if(linea == null) {
				
				throw new LineaNotFoundException(id);
				
			}else {
				
				linea.setProducto(this.serviceProducto.findById(lineaPedido.getProducto().getId()));
				linea.setCantidad(lineaPedido.getCantidad());
				LineaPedido nuevaLinea = this.serviceLinea.edit(linea);
				
				return nuevaLinea;
			}
			
		}
		
		/**
		 * PARA ELIMINAR UNA LÍNEA DE PEDIDO
		 * Hago una petición DELETE a http://localhost:8080/linea/id y se elimina la línea que coincide con el id pasado por parámetro
		 * @param id
		 * @return JSON vacío 
		 */
		@DeleteMapping("pedido/{idPedido}/lineaPedido/{id}")
		public ResponseEntity<?> deleteLineaPedido(@PathVariable Integer id) {
			
			LineaPedido linea = this.serviceLinea.findById(id);
			
			if (linea == null) {
				throw new LineaNotFoundException(id);
			
			}else {
				
				this.serviceLinea.borrar(id);	//borro la línea de la BBDD
				return ResponseEntity.noContent().build();
			}
		}
			
		/**
		 * GESTIÓN DE EXCEPCIÓN DE JSON MAL FORMADO
		 * @param ex
		 * @return un json con el estado, fecha, hora y mensaje de la excepción --> ignora la traza de la excepción
		 */
		@ExceptionHandler(JsonMappingException.class)
		public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.BAD_REQUEST);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		}
			
	

}
