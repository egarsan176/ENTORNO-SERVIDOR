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
	
	// REPOSITORIOS
	@Autowired
	private UsuarioRepository usuarioREPO;
	
	@Autowired
	private PedidoRepository pedidoREPO;
	
	/**
	 * ENCUENTRA LA LISTA DE TODOS LOS USUARIOS DEL REPOSITORIO
	 * @return list con los usuarios de la BBDD
	 */
	public List<Usuario> findAllUsers(){
		return this.usuarioREPO.findAll();
	}
	
	/**
	 * AÑADIR UN USUARIO AL REPOSITORIO
	 * @param usuario
	 * @return
	 */
	public Usuario add(Usuario usuario) {
		return this.usuarioREPO.save(usuario);
	}
	
	/**
	 * BUSCAR UN USUARIO A TRAVÉS DE SU ID
	 * @param id
	 * @return usuario que coincide con ese id
	 */
	public Usuario findById(Long id) {
		return this.usuarioREPO.findById(id).orElse(null);
	}
	
	/**
	 * BUSCA UN USUARIO A TRAVÉS DE SU NOMBRE DE USUARIO (username)
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
	 * COMPRUEBA QUE EXISTA UN USUARIO A TRAVÉS DE SU USERNAME Y CONTRASEÑA
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


	

	

}
