package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

@Controller
public class ModelController {
	
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
	 
	 /**
	  * Proporciona el login y crea un usuario vacío nada más inicializar la web
	  * @param model
	  * @return la pagina de login
	  */
	 @GetMapping({"/", "/login"})
	 public String newLoginUsuario(Model model) {
		 model.addAttribute(usuarioString, new Usuario());
		return "login";
		 
	 }
	 
	 /**
	  * Este método invalida la sesión del usuario
	  * @return pagina de login
	  */
	 @GetMapping({"/cerrarSesion"})
		public String cerrarSesion() {
		
			sesion.invalidate();
		return redirigirLogin;
		}
	 
	 /**
	  * Este método comprueba que el login introducido existe  y da paso a la siguiente ventana
	  * @param usuario que hace login
	  * @return ventana de opciones si el login es correcto. Permaneces en el login si no es correcto
	  */
	 @PostMapping("/login/submit")
	 public String validarUsuario(@ModelAttribute("usuario") Usuario nuevoUsuario, BindingResult bindingResult) {
		 
		if (!servicioUser.isFindUser(nuevoUsuario.getUserName(), nuevoUsuario.getPassword())
				&& !bindingResult.hasErrors()) {
			return redirigirLogin;
		}else {
			
			//almaceno en la sesión al usuario introducido
			this.sesion.setAttribute(usuarioString, servicioUser.getByUsername(nuevoUsuario.getUserName()));
			
			return "/opcionesUsuario";
		}
		 
	 }
	 
	 @GetMapping("/lista")
	 public String listaDeUsuarios(Model model) {
		 model.addAttribute("listaUsuarios", servicioUser.findAll());
		 return "lista";
	 }
	 
	 /**
	  * Este método muestra una tabla con los pedidos del usuario de la sesión
	  * @param model listaDePedidos
	  * @return html listarPedidos
	  */
	 @GetMapping("/listarPedidos")
	 public String listarPedidos(Model model) {
		 if(sesion.getAttribute(usuarioString) == null) {
			 return redirigirLogin;
		 }else {
			 Usuario user = (Usuario) sesion.getAttribute(usuarioString);
			 
			 model.addAttribute(usuarioString, user);
			 //CARGO UN PEDIDO POR DEFECTO PARA COMPROBAR QUE FUNCIONA
			 //this.servicioPedido.init();
			 //this.servicioUser.addPedido(user, this.servicioPedido.getAll(), user.getDireccion());
			 //System.out.println(user.getPedidos()); //ha funcionado porque se muestra el pedido asociado
			 
			 model.addAttribute("listaDePedidos", this.servicioPedido.findPedidoUser(user));
			 
			 return "listarPedidos";
		 }
	 }
	 
	 /**
	  * Este método muestra un formulario para realizar un nuevo pedido
	  * @param model listaProductos
	  * @return si el usuario está en la sesión, muestra el formulario; si no está te lleva al login
	  */
	 @GetMapping("/opcionesUsuario/nuevoPedido")
	 public String nuevoPedido(Model model) {
		 if(sesion.getAttribute(usuarioString)==null) {
			 return redirigirLogin;
		 }else {
			 model.addAttribute("listaDeProductos", this.serviceProducto.findAll());
			 return "/nuevoPedido";
		 }
	 }
	 /**
	  * Este método guarda los productos y cantidades introducidos por el usuario al hacer un pedido nuevo
	  * @param model
	  * @param listaCantidades
	  * @return si el usuario está en la sesión y ha hecho un pedido, pasa a la página del resumen del pedido
	  * si no está en la sesión, pasa al login
	  * si está en la sesión pero no ha pedido nada, pasa a la página de opciones
	  * 
	  */
	 @PostMapping("/nuevoPedido/submit")
	 public String mostrarPedido(Model model, @RequestParam(required=false,name="cantidad") Integer [] listaCantidades) {
		 int contador = 0;
		 
		 if(sesion.getAttribute(usuarioString)!=null) {
			 
			 for(int i =0; i<listaCantidades.length; i++) {
				 if(listaCantidades[i] == null) {
					 return "redirect:/opcionesUsuario/nuevoPedido";
				 }else if(listaCantidades[i]>0) {
					 contador++;
				 }
			 }
			 if(contador>0) {
				 this.servicioPedido.addProducto(listaCantidades);
				 model.addAttribute("listaCantidadYProducto", this.servicioPedido.getAll());
				 model.addAttribute(usuarioString, sesion.getAttribute("usuario"));
				 
				 return "/resumenPedido";
			 }
		 }
		 return redirigirLogin;
	 }
	 

	 /**
	  * Este método muestra la lista de pedidos del usuario al añadir un nuevo pedido
	  * @param model
	  * @return si no está en la sesión, pasa al login
	  * si está en la sesión, te muestra la lista de pedidos del usuario
	  * si el envío es nulo, te lleva a un nuevo pedido
	  */
	 @PostMapping("/nuevoPedido/listarPedidos")
	 public String listarnuevoPedido(Model model, @RequestParam(required=false,value="envio") String envio) {
		 if(sesion.getAttribute(usuarioString) == null){
			 return redirigirLogin;
		 }else if(envio == null) {
			 return "redirect:/opcionesUsuario/nuevoPedido";
		 }
		 else {
			 Usuario user = (Usuario) sesion.getAttribute(usuarioString);
			 this.servicioUser.addPedido(user, this.servicioPedido.getAll(), envio);

			 model.addAttribute("listaDePedidos", this.servicioPedido.findPedidoUser(user));
			 
			 return redirigirListarPedidos; //redirect para que no duplique valores al repetir la petición
		 }
		 
	 }
	 
	 /**
	  * Este método borra un pedido del usuario
	  * @param model
	  * @param ref
	  * @return si no está en la sesión, pasa al login
	  * si está en la sesión, te devuelve a la lista de pedidos del usuario
	  */
	 @GetMapping("/pedido/borrar/{ref}")
	 public String borrarPedido(Model model, @PathVariable String ref) {
		 
		 if(sesion.getAttribute(usuarioString) == null){
			 return redirigirLogin;
		 }else {
			 Usuario user = (Usuario) sesion.getAttribute(usuarioString);
			 
			 Pedido pedido = this.servicioUser.getPedidoByRef(ref, user);
			 this.servicioUser.eliminarPedido(pedido, user);
			 
			 model.addAttribute("listaDePedidos", this.servicioPedido.findPedidoUser(user));
			 
			 return redirigirListarPedidos;
		 }
		 
		 
	 }
	 
	 /**
	  * Este método recibe el pedido que coincide con la referencia que se le pasa 
	  * @param model
	  * @param ref
	  * @return pasa al html del login si el usuario no está en la sesión
	  * pasa al html de editar si el usario está en la sesión
	  */
	 @GetMapping("/pedido/editar/{ref}")
	 public String editarPedido(Model model, @PathVariable String ref) {
		 if(sesion.getAttribute(usuarioString) == null){
			 return redirigirLogin;
		 }else {
			 Usuario user = (Usuario) sesion.getAttribute(usuarioString);
			 
			 Pedido pedido = this.servicioUser.getPedidoByRef(ref, user);
			 model.addAttribute("pedido", pedido);
			 model.addAttribute(usuarioString, user);
			 model.addAttribute("listaCantidadYProducto", pedido.getListaDeProductos());
			 
			 return "/editar";
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
			 @RequestParam (required=false, value="ref") String ref,
			 @RequestParam (required=false, value="telefono") String telefono,
			 @RequestParam (required=false, value="email") String email,
			 @RequestParam (required=false, value="direccion") String direccion,
			 @RequestParam (required=false, value="cantidad") Integer [] listaDeCantidades,
			 @RequestParam (required=false, value="envio") String envio) {
		 
		 if(sesion.getAttribute(usuarioString) == null){
			 return redirigirLogin;
		 }else {
			 Usuario usuario = (Usuario) sesion.getAttribute(usuarioString);
			 this.servicioPedido.editarPedido(ref, email, telefono, direccion, listaDeCantidades, envio, usuario);
			 return redirigirListarPedidos;
		 }
		 
	 }
	 
	 
	 
	 
	 
	 

}
