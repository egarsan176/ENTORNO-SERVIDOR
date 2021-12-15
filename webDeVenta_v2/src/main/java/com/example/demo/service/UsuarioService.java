package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private PedidoService servicioPedido;

	private List<Usuario> listaUsuarios = new ArrayList<>();
	
	/**
	 * Este método devuelve la lista de usuarios
	 * @return lista
	 */
	public List<Usuario> findAll() {
		return listaUsuarios;
	}
	
	/**
	 * Este método te devuelve un usuario buscándolo a través del userName
	 * @param userName
	 * @return usuario que corresponde al userName que se le pasa por parámetro
	 */
	public Usuario getByUsername(String userName) {
		Usuario result = null;
		
		boolean isEncontrado = false;
		int indice = 0;
		
		while(!isEncontrado && indice<listaUsuarios.size()) {
			if(Objects.equals(listaUsuarios.get(indice).getUserName(), userName)) {
				isEncontrado = true;
				result = listaUsuarios.get(indice);
			}else {
				indice++;
			}
		}

		return result;
	}
	
	/**
	 * Este método comprueba que un usuario existe a través del userName y la contraseña
	 * @param userName
	 * @param password
	 * @return false si el usuario no existe, y si existe true
	 */
	public boolean isFindUser(String userName, String password) {
		boolean isEncontrado = false;
		int indice = 0;
		
		while (!isEncontrado && indice<listaUsuarios.size()) {
			if(listaUsuarios.get(indice).getUserName().equals(userName) 
					&& listaUsuarios.get(indice).getPassword().equals(password)){
				isEncontrado = true;
			}else {
				indice++;
			}
		}
		
		
		return isEncontrado;
	}
	
	/**
	 * Este método inicializa el array de usuarios 
	 */
	@PostConstruct
	public void init() {
		listaUsuarios.addAll(
				Arrays.asList(new Usuario("Juan", "juan123","juan23@gmail.com", "666444888", "C/Caracola 6, 41109 Sevilla", "28632105D", "123456"),
				new Usuario("Olga", "olga12","olgaFdz1@gmail.com", "600333111", "C/Medusa 63, 41109 Sevilla", "41256301L", "111111"),
				new Usuario("Pedro", "pelopez","pelopez@gmail.com", "777444999", "C/Pulpo 4, 41109 Sevilla", "28510639M", "000000")));
	
	}
	
	/**
	 * Este  método añade un pedido al usuario
	 * @param user
	 * @param listaDeProductos
	 * @param envio
	 */
	public void addPedido(Usuario user, Map<Producto, Integer> listaDeProductos, String envio, double precioTotal) {
		
		Pedido pedido = new Pedido(listaDeProductos, envio);
		//establezco por defecto las propiedades del pedido para que coincidan con las del usuario
		pedido.setDireccion(user.getDireccion());
		pedido.setEmail(user.getEmail());
		pedido.setTelefono(user.getTelefono());
		pedido.setListaDeProductos(listaDeProductos);
		pedido.setEnvio(envio);
		pedido.setCosteTotalPedido(precioTotal);
		
		user.addPedido(pedido);
		}
	/**
	 * Este método devuelve la lista con todos los pedidos de un usuario
	 * @param usuario
	 * @return lista de pedidos
	 */
	public List<Pedido> getAllPedidos(Usuario usuario) {
		return usuario.getPedidos();
	}
	
	/**
	 * Este método devuelve un pedido según una referencia introducida y un usuario
	 * @param ref
	 * @param user
	 * @return pedido
	 */
	public Pedido getPedidoByRef(Integer ref, Usuario user) {
		Pedido pedido = new Pedido(ref);
		for(int i=0; i<user.getPedidos().size(); i++) {
			if(user.getPedidos().get(i).getRef() == (pedido.getRef())) {
				pedido = user.getPedidos().get(i);
			}
		}
		return pedido;
	}
	/**
	 * Este método borra un pedido concreto del usuario
	 * @param pedido
	 * @param user
	 */
	public void eliminarPedido(Pedido pedido, Usuario user) {
		
		user.getPedidos().remove(pedido);
	}
	
}
