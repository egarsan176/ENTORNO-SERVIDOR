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
	 
	 
	 /**
	  * Este método invalida la sesión del usuario
	  * @return pagina de login
	  */
	 @GetMapping({"/cerrarSesion"})
	 public String cerrarSesion() {
	
		this.sesion.invalidate();
		return "redirect:/login";
	 }
	 
		/**
		 * Pagina de error al opciones de envio del pedido
		 * @return devuelve al login si no existe la sesion o la pagina de error
		 */
		 @GetMapping({"/error"})
		 public String mostrarPaginaDeError() {
			
			if(sesion.getAttribute("usuario")!=null) {
				return "error";
			}
			return "redirect:/login";
			
		 }

	 
	 /**
	  * Proporciona el login y crea un usuario vacío nada más inicializar la web para añadirlo al modelo
	  * @param model
	  * @return la pagina de login
	  */
	 @GetMapping({"/", "/login"})
	 public String newLoginUsuario(Model model) {
		 model.addAttribute("usuario", new Usuario());
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
			 //recupero al usuario de la BBDD
			 Usuario usuBD = this.servicioUser.findByUsername(nuevoUsuario.getUserName());
			 
			 this.sesion.setAttribute("idUsuario", usuBD.getId());
			 
			 //paso el usuario al modelo
			 model.addAttribute("usuario", usuBD);
			 
			 
			 return "/opcionesUsuario";
		 }
		  
	 }
	 
	 /**
	  * Las opciones al pasar del login  y al hacer click en el botón ATRÁS de la lista de pedidos
	  * @param model
	  * @return
	  */
	 @GetMapping("/opcionesUsuario")
	 public String verOpciones(Model model) {
		 
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
		 }else {

			//recupero al usuario de la BBDD
			 Usuario usuBD = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));
			//lo paso al modelo
			 model.addAttribute("usuario", usuBD);
			 
			 
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
		 //si el usuario no está en la sesión redirijo al login
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
		 }
		 else {
			//recupero al usuario de la BBDD
			 Usuario usuBD = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));

			 //añado al modelo el usuario para poder mostrarlo en /listarPedidos
			 model.addAttribute("usuario", usuBD);
			 
			 //PARA ELIMINAR LOS PEDIDOS QUE SE CARGAN POR DEFECTO AL ENTRAR EN NUEVO PEDIDO
			  Pedido pedido = this.servicioPedido.getPedidoSinLineas(usuBD.getId());
			 
//			 Pedido pedido = new Pedido();
//			 
//			 //recorro la lista de todos los pedidos del usuario y si existen pedidos vacíos sin lineas asociadas, los borro
//			 for (int i = 0; i<this.servicioPedido.findListaPedidosUser(usuBD.getId()).size(); i++) {
//				 if(this.servicioPedido.findListaPedidosUser(usuBD.getId()).get(i).getListadoLineasPedido().size() == 0) {
//					 pedido = servicioPedido.findListaPedidosUser(usuBD.getId()).get(i);
//				 }
//				 
//			 }
			 
			 this.servicioPedido.eliminarPedido(pedido);

			 
			 //añado al modelo los pedidos de la BBDD de ese usuario para poder mostrarlo en /listarPedidos
			 model.addAttribute("listaDePedidos", this.servicioPedido.findListaPedidosUser(usuBD.getId()));
			 
			 
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
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
		 }else 
		 {
			 model.addAttribute("produ", new Producto());
			 
			 //pasamos al modelo la lista de productos que hay almacenada en la bbdd
			 model.addAttribute("listaDeProductos", this.serviceProducto.findAllProducts());
			 
			 //usuario de la BBDD y lo añado al modelo
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));
			 model.addAttribute("usuarioID", user.getId());
			 
			 //creo un nuevo pedido,se lo asocio al usuario y le añado datos iniciales
			 Pedido pedido = new Pedido();
			 pedido.setUsuario(user);
			 this.servicioPedido.establecerDatosInicialesPedido(pedido, user);
			 System.out.println("pedido con datos actualizados memoria : "+pedido.toString());
			
			 //añado el pedido a la base de datos y al añadirlo, se le genera el id
			 this.servicioPedido.addPedidoaLaBBDD(pedido);
			 
			 //paso el id del pedido al modelo
			 model.addAttribute("pedidoID", pedido.getId());
			 
			 System.out.println("pedido con datos actualizados de la BBDD : "+this.servicioPedido.findPedido(pedido.getId()).toString());
		
			 
			 return "nuevoPedido";
		 }
	 }
	 
	 /**
	  * A este método se accede cada vez que pinchamos en añadir al carrito de un producto
	  * @param model
	  * @param produ
	  * @return
	  */
	 @PostMapping("/opcionesUsuario/nuevoPedido")
	 public String nuevoPedidoProducto(Model model, @ModelAttribute("produ")Producto product, 
			 @RequestParam(required=false,name="unidades") Integer unidades,
			 @RequestParam(required=false,name="usuarioID") Long usuarioID,
			 @RequestParam(required=false,name="pedidoID") Integer pedidoID
			 ) {
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
		 }else {
			 
			 //se crea un nuevo producto y se muestra el catálogo cada vez que se llega aquí (después de hacer click en el botón añadir)
		 
			 model.addAttribute("produ", new Producto());
			 model.addAttribute("listaDeProductos", this.serviceProducto.findAllProducts());
			 model.addAttribute("pedidoID", pedidoID);
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
		 
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
			 }
		 else {
			 System.out.println(pedidoID +"pedido ID nuevoPEdido/submit POST");
			 //recupero al usuario
			 Usuario usu = this.servicioUser.findById(usuarioID);
			 
			 //recupero el id del pedido y traigo el pedido correspondiente
			 Pedido pedido = this.servicioPedido.findPedido(pedidoID);
			 
			 //modifico el precio del pedido
			pedido.setCosteTotalPedido(this.servicioPedido.calcularPrecioTotal(pedido));

			//añado el pedido a la base de datos
			this.servicioPedido.addPedidoaLaBBDD(pedido);
			
			System.out.println("submit nuevo pedido guardado en BD : "+this.servicioPedido.findPedido(pedidoID).toString());
			 
			model.addAttribute("lineaDePedidos", this.servicioPedido.findPedido(pedidoID).getListadoLineasPedido()); 
			model.addAttribute("usuario", usu);
			model.addAttribute("pedido", pedido);
			model.addAttribute("pedidoID", pedidoID);
			
					 
			return "/resumenPedido";	//cuando doy a cerrar el resumen me lleva a listarPedidos   en pasado no barra
		 }

	 }
	 
	 /**
	  * A este método se accede tras confirmar un pedido en el resumen y nos muestra la lista de pedidos del usuario
	  * @param model
	  * @param envio
	  * @param ref
	  * @return si no hay usuario en la sesión, nos lleva al login
	  * si el envío es null, al nuevoPedido
	  * si todo es correcto redirije a la lista de pedidos del usuario
	  */
	 @PostMapping("/nuevoPedido/listarPedidos")
	 public String listarNuevoPedido(Model model, @RequestParam(required=false,name="envio") String envio,
			 @RequestParam(required=false,name="pedidoID") Integer id) {

		
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
			 }
		 
		 else if(envio == null) {
			 return "redirect:/opcionesUsuario/nuevoPedido";
		 }else {
			 System.out.println("id pedido: "+id);
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));
			 Pedido pedido = this.servicioPedido.findPedido(id);
			 //System.out.println(pedido.getEnvio());
			 pedido.setEnvio(envio);
			 
			 //añado el pedido a la base de datos
			 this.servicioPedido.addPedidoaLaBBDD(pedido);
			  
			 
			 model.addAttribute("pedido", this.servicioPedido.findPedido(id));
			 model.addAttribute("listaDePedidos", this.servicioPedido.findListaPedidosUser(user.getId()));
			 model.addAttribute("usuario", user);
			 
			 
			 return "redirect:/listarPedidos";
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
		 
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
			 }
		 else {
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));	 
			 
			 model.addAttribute("usuario", user);
			 model.addAttribute("lineaDePedidos", this.servicioPedido.findPedido(id).getListadoLineasPedido());
			 model.addAttribute("pedido", this.servicioPedido.findPedido(id));
			 
			 
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
		 
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
			 }
		 else {
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));
			 
			 Pedido pedido = this.servicioPedido.findPedido(id);
			 this.servicioPedido.eliminarPedido(pedido);
			 
			 model.addAttribute("listaDePedidos", this.servicioPedido.findListaPedidosUser(user.getId()));
			
			 
			 return "redirect:/listarPedidos";
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
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
			 }
		 else {
			 System.out.println(id+"id editar");
			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));
			 
			 Pedido pedido = this.servicioPedido.findPedido(id);
			 
			 model.addAttribute("lineaDePedidos", pedido.getListadoLineasPedido());
			 model.addAttribute("pedido", pedido);
			 model.addAttribute("usuario", user);
			 
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
		 
		 if(this.sesion.getAttribute("idUsuario")== null) {
			 return "redirect:/login";
			 }
		 else if("".equals(telefono) || "".equals(email) || "".equals(direccion) || listaDeCantidades.length==0 || "".equals(envio)){
			 return "/error";
		 }else {
			 Usuario usuario = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));
			 
			 
			 Pedido pedido = this.servicioPedido.editarPedido(id, email, telefono, direccion, listaDeCantidades, envio, usuario);

			 pedido.setCosteTotalPedido(this.servicioPedido.calcularPrecioTotal(pedido));
			 this.servicioPedido.addPedidoaLaBBDD(pedido);
		 
			 model.addAttribute("pedido", this.servicioPedido.findPedido(id));	//lo añado de este modo para que me coja los cambios de la bbdd
			 model.addAttribute("usuario", usuario);
			 model.addAttribute("listaDePedidos", this.servicioPedido.findListaPedidosUser(usuario.getId()));
			 
			 
			 return "redirect:/listarPedidos";		//"redirect:/listarPedidos"
		 }
		 
	 }
	 
	 

}
//	 @PostMapping("/pedido/editar/{id}")
//	 public String editarPedidoLINEA(Model model, @PathVariable Integer id, 
//			 @ModelAttribute("linea")LineaPedido linea, 
//			 @RequestParam(required=false,name="cantidad") Integer cantidad,
//			 @RequestParam(required=false,name="usuarioID") Long usuarioID,
//			 @RequestParam(required=false,name="productoID") Integer productoID){
//		 if(this.sesion.getAttribute("idUsuario")== null) {
//			 return "redirect:/login";
//			 }
//		 else {
//			 
//			 Usuario user = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));
//			 
//			 Pedido pedido = this.servicioPedido.findPedido(id);
//			 System.out.println(pedido);
//			 
//			 model.addAttribute("linea", new LineaPedido());
//			 model.addAttribute("lineaDePedidos", pedido.getListadoLineasPedido());
//			 model.addAttribute("pedido", pedido);
//			 model.addAttribute("usuario", user);
//			 
//			 //se añaden al pedido las líneas de pedido con cada producto y su cantidad
//			 //servicioPedido.addLineaPedido(pedido, productoID, cantidad);
//			 servicioPedido.addLineaEdicion(linea, id);
//			 System.out.println(productoID +"produID");
//			 System.out.println(cantidad+"cantidad");
//			 System.out.println(id+"id pedido");
//			 System.out.println(linea);
//			 
//			 
//			 
//			 
//			 return "editar"; 
//	 }
//	 }
//	 
//	 
//	 
//	 /**
//	  * Este método recibe los parámetros que se pueden editar en el html de editar y cambia los datos antiguos por los nuevos
//	  * @param ref
//	  * @param telefono
//	  * @param email
//	  * @param direccion
//	  * @param listaDeCantidades
//	  * @param envio
//	  * @return pasa al html del login si el usuario no está en la sesión
//	  * pasa al html de listarPedidos si el usuario está en la sesión
//	  */
//	 @PostMapping("/editar/submit")
//	 public String editarPedidoSubmit(
//			 @RequestParam (required=false, value="id") Integer id,
//			 @RequestParam (required=false, value="telefono") String telefono,
//			 @RequestParam (required=false, value="email") String email,
//			 @RequestParam (required=false, value="direccion") String direccion,
//			 //@RequestParam (required=false, value="cantidad") Integer [] listaDeCantidades,
//			 @RequestParam (required=false, value="envio") String envio,
//			 Model model) {
//		 
//		 if(this.sesion.getAttribute("idUsuario")== null) {
//			 return "redirect:/login";
//			 }
////		 else if("".equals(telefono) || "".equals(email) || "".equals(direccion) || listaDeCantidades.length==0 || "".equals(envio)){
////			 return "/error";
////		 }
//		 else {
//			 Usuario usuario = this.servicioUser.findById((Long) sesion.getAttribute("idUsuario"));
//			 
//			 //this.servicioPedido.editarPedido(id, email, telefono, direccion, listaDeCantidades, envio, usuario);
//			 
//			 Pedido pedido = this.servicioPedido.editarPedido(id, email, telefono, direccion, envio, usuario);
//			 System.out.println(pedido+"edit");
//			 
//			 this.servicioPedido.addPedidoaLaBBDD(pedido);
//			 System.out.println(this.servicioPedido.findPedido(id).toString()+"pedidoBD");
//			 
//			 model.addAttribute("pedido", pedido);	//lo añado de este modo para que me coja los cambios de la bbdd
//			 model.addAttribute("usuario", usuario);
//			 model.addAttribute("listaDePedidos", this.servicioPedido.findListaPedidosUser(usuario.getId()));
//			 
//			 
//			 return "redirect:/listarPedidos";		//"redirect:/listarPedidos"
//		 }
//		 
//	 }
	 

