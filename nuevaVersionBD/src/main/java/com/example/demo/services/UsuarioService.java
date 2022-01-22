package com.example.demo.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	// instancio los repositorios
	@Autowired
	private UsuarioRepository usuarioREPO;
	
	@Autowired
	private PedidoRepository pedidoREPO;
	
	//para poder controlar si el usuario está logueado o no y que no se pueda acceder a cualquier página
	private boolean isLogueado = false;
	
	/**
	 * Este método devuelve el listado de usuarios de la BBDD
	 * @return list con los usuarios de la BBDD
	 */
	public List<Usuario> findAllUsers(){
		return this.usuarioREPO.findAll();
	}
	
	/**
	 * Este método añade un usuario al respositorio de la BBDD
	 * @param usuario
	 * @return
	 */
	public Usuario add(Usuario usuario) {
		return this.usuarioREPO.save(usuario);
	}
	
	/**
	 * Este método busca un usuario en la BBDD a través del id que se le pasa por parámetro
	 * @param id
	 * @return usuario que coincide con ese id
	 */
	public Usuario findById(Long id) {
		return this.usuarioREPO.findById(id).orElse(null);
	}
	
	/**
	 * Este método comprueba que un usuario existe en la BBDD y te lo devuelve
	 * @param userName
	 * @return usuario que corresponde al userName que se le pasa por parámetro
	 */
	public Usuario findByUsername(String userName) {
		Usuario result = null;
		
		boolean isEncontrado = false;
		int indice = 0;
		
		while(!isEncontrado && indice<this.usuarioREPO.findAll().size()) {
			if(Objects.equals(this.usuarioREPO.findAll().get(indice).getUserName(), userName)) {
				isEncontrado = true;
				result = this.usuarioREPO.findAll().get(indice);
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
		
		while (!isEncontrado && indice<this.usuarioREPO.findAll().size()) {
			if(this.usuarioREPO.findAll().get(indice).getUserName().equals(userName) 
					&& this.usuarioREPO.findAll().get(indice).getPassword().equals(password)){
				isEncontrado = true;
			}else {
				indice++;
			}
		}
		
		
		return isEncontrado;
	}

	/**
	 * para recuperar si un usuario está logueado o no
	 * @return
	 */
	public boolean isLogueado() {
		return isLogueado;
	}

	/**
	 * para cambiar el estado de la propiedad isLogueado
	 * @param isLogueado
	 */
	public void setLogueado(boolean isLogueado) {
		this.isLogueado = isLogueado;
	}
	

	

}
