package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.services.PedidoService;
import com.example.demo.services.ProductoService;
import com.example.demo.services.UsuarioService;

@Controller
public class MainController {
	
	//INYECCIÓN DE LA SESIÓN
	 @Autowired
	 private HttpSession sesion;
	 
	//INYECCIÓN DE LOS DISTINTOS SERVICIOS
	 @Autowired
	 private UsuarioService servicioUser;
	 
	 @Autowired
	 private PedidoService servicioPedido;
	 
	 @Autowired
	 private ProductoService serviceProducto;
	 
	//USO DE CONSTANTES PARA NO DUCPLICAR LITERALES (SONARLINT)
	 private static String redirigirListarPedidos = "redirect:/listarPedidos";
	 private static String redirigirLogin = "redirect:/login";
	 private static String usuarioString = "usuario";
	 private static String lineaDePedidos = "lineaDePedidos";
	 private static String listaDePedidos = "listaDePedidos";
	 private static String pedidoString = "pedido";
	 private static String pedidoIDString = "pedidoID";
	 private static String idUsuario = "idUsuario";
	 
	 
	 /**
	  * Este método invalida la sesión del usuario
	  * @return pagina de login
	  */
	 @GetMapping({"/cerrarSesion"})
	 public String cerrarSesion() {
	
		this.sesion.invalidate();
		return redirigirLogin;
	 }
	 
		/**
		 * Pagina de error al opciones de envio del pedido
		 * @return devuelve al login si no existe la sesion o la pagina de error
		 */
		 @GetMapping({"/error"})
		 public String mostrarPaginaDeError() {
			
			if(sesion.getAttribute(usuarioString)!=null) {
				return "error";
			}
			return redirigirLogin;
			
		 }

	 
	 /**
	  * Proporciona el login y crea un usuario vacío nada más inicializar la web para añadirlo al modelo
	  * @param model
	  * @return la pagina de login
	  */
	 @GetMapping({"/", "/login"})
	 public String newLoginUsuario(Model model) {
		 model.addAttribute(usuarioString, new Usuario());
		return "login";
		 
	 }
	 
	 /**
	  * Este método ocurre cuando se da al submit del login
	  * Valida los datos del usuario y si son correctos, pasa a la página de opciones del usuario
	  * @param usuario que hace login
	  * @return ventana de opciones si el login es correcto. Permaneces en el login si no es correcto
	  */
	 @PostMapping("/login/submit")
	 public String validarUsuario(@Valid @ModelAttribute("usuario") Usuario nuevoUsuario, Model model) {
		 //compruebo si el usuario está en la base de datos y si no se encuentra te quedas en el login
		 if( ! this.servicioUser.isFindUser(nuevoUsuario.getUserName(), nuevoUsuario.getPassword()) ) {
			  return "login";
		 }
		 
		 else {
			 //cambio la propiedad logueado para poder acceder a otras páginas
			 this.servicioUser.setLogueado(true);
			 
			 //recupero al usuario de la BBDD y almaceno su id en la sesión
			 Usuario usuBD = this.servicioUser.findByUsername(nuevoUsuario.getUserName());
			 this.sesion.setAttribute(idUsuario, usuBD.getId());
			 
			 //paso el usuario al modelo
			 model.addAttribute(usuarioString, usuBD);
			 
			 
			 return "/opcionesUsuario";
		 }
		  
	 }
	 
	 /**
	  * Muestra las opciones al pasar del login  y al hacer click en el botón ATRÁS de la lista de pedidos
	  * @param model
	  * @return
	  */
	 @GetMapping("/opcionesUsuario")
	 public String verOpciones(Model model) {
		
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }else {

			//recupero al usuario de la BBDD
			 Usuario usuBD = this.servicioUser.findById((Long) sesion.getAttribute(idUsuario));
			//lo paso al modelo
			 model.addAttribute(usuarioString, usuBD);
			 
			 
			 return "/opcionesUsuario";
		 }
	 }
	 
	 /**
	  * A este método se accede al pinchar en el botón LISTAR PEDIDOS del opcionesUsuario
	  * @param model listaDePedidos
	  * @return html listarPedidos
	  */
	 @GetMapping("/listarPedidos")
	 public String listarPedidos(Model model) {
		
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }
		 else {
			//recupero al usuario de la BBDD
			 Usuario usuBD = this.servicioUser.findById((Long) sesion.getAttribute(idUsuario));

			 //añado al modelo el usuario para poder mostrarlo en /listarPedidos
			 model.addAttribute(usuarioString, usuBD);
			 
			 //PARA ELIMINAR LOS PEDIDOS QUE SE CARGAN POR DEFECTO AL ENTRAR EN NUEVO PEDIDO
			 Pedido pedido = this.servicioPedido.getPedidoSinLineas(usuBD.getId());
			 this.servicioPedido.eliminarPedido(pedido);
			 
			 //añado al modelo los pedidos de la BBDD de ese usuario para poder mostrarlo en /listarPedidos
			 model.addAttribute(listaDePedidos, this.servicioPedido.findListaPedidosUser(usuBD.getId()));
			 
			 
			 return "listarPedidos";
		 }
		 
		 
	 }
	 
	 
	 /**
	  * A este método se accede al seleccionar el botón CREAR PEDIDO del opcionesUsuario
	  * Se pinta el formulario para crear un pedido
	  * @param model listaProductos
	  * @return si el usuario está en la sesión, muestra el formulario; si no está te lleva al login
	  */
	 @GetMapping("/opcionesUsuario/nuevoPedido")
	 public String nuevoPedido(Model model) {
		 
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }else 
		 {	//añado al modelo el nuevo producto que se crea con el formulario
			 model.addAttribute("produ", new Producto());
			 
			 //pasamos al modelo la lista de productos que hay almacenada en la bbdd
			 model.addAttribute("listaDeProductos", this.serviceProducto.findAllProducts());
			 
			 //usuario de la BBDD y lo añado al modelo
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute(idUsuario));
			 model.addAttribute("usuarioID", user.getId());
			 
			 //creo un nuevo pedido,se lo asocio al usuario y le añado datos iniciales
			 Pedido pedido = new Pedido();
			 pedido.setUsuario(user);
			 this.servicioPedido.establecerDatosInicialesPedido(pedido, user);
						
			 //añado el pedido a la base de datos y al añadirlo, se le genera el id
			 this.servicioPedido.addPedidoaLaBBDD(pedido);
			 
			 //paso el id del pedido al modelo
			 model.addAttribute(pedidoIDString, pedido.getId());
			 
					 
			 return "nuevoPedido";
		 }
	 }
	 
	 /**
	  * A este método se accede cada vez que pinchamos en añadir al carrito de un producto
	  * @param model
	  * @param produ
	  * @return una nueva linea del pedido con cada producto que se selecciona y su cantidad
	  */
	 @PostMapping("/opcionesUsuario/nuevoPedido")
	 public String nuevoPedidoProducto(Model model, @ModelAttribute("produ")Producto product, 
			 @RequestParam(required=false,name="unidades") Integer unidades,
			 @RequestParam(required=false,name="usuarioID") Long usuarioID,
			 @RequestParam(required=false,name="pedidoID") Integer pedidoID
			 ) {
		 
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }else {
			 
			 //se crea un nuevo producto y se muestra el catálogo cada vez que se llega aquí (después de hacer click en el botón añadir)
		 
			 model.addAttribute("produ", new Producto());
			 model.addAttribute("listaDeProductos", this.serviceProducto.findAllProducts());
			 model.addAttribute(pedidoIDString, pedidoID);
			 model.addAttribute("usuarioID", usuarioID);
			 
			 //extraigo el pedido a través de su ID
			 Pedido pedido = this.servicioPedido.findPedido(pedidoID);
		 
			 //se añaden al pedido las líneas de pedido con cada producto y su cantidad
			 servicioPedido.addLineaPedido(pedido, product.getId(), unidades);
			 
			 	 
			 return "nuevoPedido";
		 }
	 }
	 
	 /**
	  * A este método se accede cuando se realiza un pedido del catálogo y muestra el resumen al hacer submit
	  * @param model
	  * @return si no hay usuario en la sesión, nos lleva al login
	  * si hay usuario y cantidades seleccionadas nos lleva al resumen del pedido
	  */
	 @PostMapping("/nuevoPedido/submit")
	 public String mostrarPedido(Model model, 
			 @RequestParam(required=false,name="pedidoID") Integer pedidoID,
			 @RequestParam(required=false,name="usuarioID") Long usuarioID) {
		 
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }
		 else {
			 
			 //recupero al usuario
			 Usuario usu = this.servicioUser.findById(usuarioID);
			 
			 //recupero el id del pedido y traigo el pedido correspondiente
			 Pedido pedido = this.servicioPedido.findPedido(pedidoID);
			 
			 //modifico el precio del pedido
			 double precio = this.servicioPedido.calcularPrecioTotal(pedido);
			 pedido.setCosteTotalPedido(precio);

			//añado el pedido a la base de datos
			this.servicioPedido.addPedidoaLaBBDD(pedido);
			model.addAttribute(lineaDePedidos, this.servicioPedido.findPedido(pedidoID).getListadoLineasPedido()); 
			model.addAttribute(usuarioString, usu);
			model.addAttribute(pedidoString, pedido);
			model.addAttribute(pedidoIDString, pedidoID);
			
					 
			return "/resumenPedido";	//cuando doy a cerrar el resumen me lleva a listarPedidos   en pasado no barra
		 }

	 }
	 
	 /**
	  * A este método se accede tras confirmar un pedido en el resumen y nos muestra la lista de pedidos del usuario
	  * @param model
	  * @param envio
	  * @param ref
	  * @return si no hay usuario en la sesión, nos lleva al login
	  * si todo es correcto redirije a la lista de pedidos del usuario
	  */
	 @PostMapping("/nuevoPedido/listarPedidos")
	 public String listarNuevoPedido(Model model, @RequestParam(required=false,name="envio") String envio,
			 @RequestParam(required=false,name="pedidoID") Integer id) {
		 
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }
		 else {
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute(idUsuario));
			 Pedido pedido = this.servicioPedido.findPedido(id);
			
			 pedido.setEnvio(envio);	
			 
			 //añado el pedido a la base de datos
			 this.servicioPedido.addPedidoaLaBBDD(pedido);
			  
			 model.addAttribute(pedidoString, this.servicioPedido.findPedido(id));
			 model.addAttribute(listaDePedidos, this.servicioPedido.findListaPedidosUser(user.getId()));
			 model.addAttribute(usuarioString, user);
			 
			 
			 return redirigirListarPedidos;
		 }
		 
	 }
	 
	 /**
	  * A este pedido accedemos con el botón VER de la lista de pedidos del usuario
	  * @param model
	  * @param ref
	  * @return
	  */
	 @GetMapping("/pedido/resumen/{id}")
	 public String verResumenPedido(Model model, @PathVariable Integer id) {
		 
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }
		 else {
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute(idUsuario));	 

			 model.addAttribute(usuarioString, user);
			 model.addAttribute(lineaDePedidos, this.servicioPedido.findPedido(id).getListadoLineasPedido());
			 model.addAttribute(pedidoString, this.servicioPedido.findPedido(id));
			 
			 
			 return "/resumenPedido";	//en anterior sin /
		 }
		 
	 }
	 
	 /**
	  * A este método se accede al pinchar en el botón BORRAR de la lista de pedidos del usuario
	  * @param model
	  * @param ref
	  * @return si no está en la sesión, pasa al login
	  * si está en la sesión, te devuelve a la lista de pedidos del usuario
	  */
	 @GetMapping("/pedido/borrar/{id}")
	 public String borrarPedido(Model model, @PathVariable Integer id) {
		 
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }
		 else {
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute(idUsuario));
			 
			 Pedido pedido = this.servicioPedido.findPedido(id);
			 this.servicioPedido.eliminarPedido(pedido);
			 
			 model.addAttribute(listaDePedidos, this.servicioPedido.findListaPedidosUser(user.getId()));
			
			 
			 return redirigirListarPedidos;
		 }
		 
	 }
	 
	 /**
	  * Este método recibe el pedido que coincide con la referencia que se le pasa y lo muestra para editarlo
	  * @param model
	  * @param ref
	  * @return pasa al html del login si el usuario no está en la sesión
	  * pasa al html de editar si el usario está en la sesión
	  */
	 @GetMapping("/pedido/editar/{id}")
	 public String editarPedido(Model model, @PathVariable Integer id) {
		 
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }
		 else {
			
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute(idUsuario));
			 
			 Pedido pedido = this.servicioPedido.findPedido(id);
			 
			 model.addAttribute(lineaDePedidos, pedido.getListadoLineasPedido());
			 model.addAttribute(pedidoString, pedido);
			 model.addAttribute(usuarioString, user);
			 
			 return "/editar";		//en pasado no barra
		 }
	 }
	 
	 /**
	  * Este método recibe los parámetros que se pueden editar en el html de editar y cambia los datos antiguos por los nuevos
	  * @param ref
	  * @param telefono
	  * @param email
	  * @param direccion
	  * @param listaDeCantidades
	  * @param envio
	  * @return pasa al html del login si el usuario no está en la sesión
	  * pasa al html de listarPedidos si el usuario está en la sesión
	  */
	 @PostMapping("/editar/submit")
	 public String editarPedidoSubmit(
			 @RequestParam (required=false, value="id") Integer id,
			 @RequestParam (required=false, value="telefono") String telefono,
			 @RequestParam (required=false, value="email") String email,
			 @RequestParam (required=false, value="direccion") String direccion,
			 @RequestParam (required=false, value="cantidad") Integer [] listaDeCantidades,
			 @RequestParam (required=false, value="envio") String envio,
			 Model model) {
		 
		 if(!servicioUser.isLogueado()) {
			 return redirigirLogin;
		 }
		 else if("".equals(telefono) || "".equals(email) || "".equals(direccion) || listaDeCantidades.length==0 || "".equals(envio)){
			 return "/error";
		 }else {
			 Usuario usuario = this.servicioUser.findById((Long) sesion.getAttribute(idUsuario));
			 
			 
			 Pedido pedido = this.servicioPedido.editarPedido(id, email, telefono, direccion, listaDeCantidades, envio, usuario);

			 pedido.setCosteTotalPedido(this.servicioPedido.calcularPrecioTotal(pedido));
			 this.servicioPedido.addPedidoaLaBBDD(pedido);
		 
			 model.addAttribute(pedidoString, this.servicioPedido.findPedido(id));	//lo añado de este modo para que me coja los cambios de la bbdd
			 model.addAttribute(usuarioString, usuario);
			 model.addAttribute(listaDePedidos, this.servicioPedido.findListaPedidosUser(usuario.getId()));
			 
			 
			 return redirigirListarPedidos;		//"redirect:/listarPedidos"
		 }
		 
	 }
	 
	 

}
