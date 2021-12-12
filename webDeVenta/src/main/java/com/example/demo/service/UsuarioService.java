package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class UsuarioService {

	private List<Usuario> listaUsuarios = new ArrayList<>();
	
	@Autowired
	private PedidoService pedidoService;
	
	/**
	 * Este método devuelve la lista de usuarios
	 * @return lista
	 */
	public List<Usuario> findAll() {
		return listaUsuarios;
	}
	
	/**
	 * Este método te devuelve un usuario
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
	 * Este método comprueba que un usuario existe
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
	
	
	
	public void addPedido(Usuario user, Map<Producto, Integer> listaDeProductos, String direccion, String envio) {
		
		Pedido pedido = new Pedido(direccion, listaDeProductos, envio);
		pedido.setListaDeProductos(listaDeProductos);
		pedido.setEnvio(envio);
		user.addPedido(pedido);
		//funciona, añade el pedido al usuario
		}
	
	public void getAllPedidos() {}
	
	/**
	 * Este método devuelve un pedido según una referencia introducida y un usuario
	 * @param ref
	 * @param user
	 * @return pedido
	 */
	public Pedido getPedidoByRef(String ref, Usuario user) {
		Pedido pedido = new Pedido(ref);
		for(int i=0; i<user.getPedidos().size(); i++) {
			if(user.getPedidos().get(i).getRef().equals(pedido.getRef())) {
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
