package com.example.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
	 
	 
	 /**
	  * Proporciona el login y crea un usuario vacío nada más inicializar la web
	  * @param model
	  * @return la pagina de login
	  */
	 @GetMapping({"/", "/login"})
	 public String newLoginUsuario(Model model) {
		 model.addAttribute("usuario", new Usuario());
		return "login";
		 
	 }
	 
	 /**
	  * Este método invalida la sesión del usuario
	  * @return pagina de login
	  */
	 @GetMapping({"/cerrarSesion"})
		public String cerrarSesion() {
		
			sesion.invalidate();
		return "redirect:/login";
		}
	 
	 /**
	  * Este método comprueba que el login introducido existe  y da paso a la siguiente ventana
	  * @param usuario que hace login
	  * @return ventana de opciones si el login es correcto. Permaneces en el login si no es correcto
	  */
	 @PostMapping("/login/submit")
	 public String validarUsuario(@ModelAttribute("usuario") Usuario nuevoUsuario) {
		//System.out.println(usuario.getUserName());
		//System.out.println(usuario.getPassword());
		
		 //bindingResult.hasErrors() && 
		if (!servicioUser.isFindUser(nuevoUsuario.getUserName(), nuevoUsuario.getPassword())) {
			return "redirect:/login";
		}else {
			
			//almaceno en la sesión al usuario introducido
			sesion.setAttribute("usuario", servicioUser.getByUsername(nuevoUsuario.getUserName()));
			
			//System.out.println(serviceProducto.findAll()); //devuelve la lista de productos
			//System.out.println(servicioPedido.getAll()); //devuelve los productos y sus cantidades
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
	 @GetMapping({"/listarPedidos" , "/nuevoPedido/listarPedidos"})
	 public String listarPedidos(Model model) {
		 if(sesion.getAttribute("usuario") == null) {
			 return "redirect:/login";
		 }else {
			 Usuario user = (Usuario) sesion.getAttribute("usuario");
			 
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
		 if(sesion.getAttribute("usuario")==null) {
			 return "redirect:/login";
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
		 
		 if(sesion.getAttribute("usuario")!=null) {
			 
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
				 return "/resumenPedido";
			 }
		 }
		 return "redirect:/login";
	 }
	 

	 /**
	  * Este método añade el nuevo pedido al usuario
	  * @param model
	  * @return si no está en la sesión, pasa al login
	  * si está en la sesión, te muestra la lista de pedidos del usuario
	  */
	 @PostMapping("/nuevoPedido/listarPedidos")
	 public String listarnuevoPedido(Model model) {
		 if(sesion.getAttribute("usuario") == null){
			 return "redirect:/login";
		 }else {
			 Usuario user = (Usuario) sesion.getAttribute("usuario");
			 this.servicioUser.addPedido(user, this.servicioPedido.getAll(), user.getDireccion());
			 model.addAttribute("listaDePedidos", this.servicioPedido.findPedidoUser(user));
			 
			 return "redirect:/listarPedidos";
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
		 
		 if(sesion.getAttribute("usuario") == null){
			 return "redirect:/login";
		 }else {
			 Usuario user = (Usuario) sesion.getAttribute("usuario");
			 
			 Pedido pedido = this.servicioUser.getPedidoByRef(ref, user);
			 this.servicioUser.eliminarPedido(pedido, user);
			 
			 model.addAttribute("listaDePedidos", this.servicioPedido.findPedidoUser(user));
			 
			 return "redirect:/listarPedidos";
		 }
		 
		 
	 }
	 
	 
	 
	 
	 
	 

}
