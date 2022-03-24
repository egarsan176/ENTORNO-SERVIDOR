package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

/**
 * Esta clase es el SERVICIO que gestiona los métodos que tienen que ver con el repositorio de Usuario
 * @author estefgar
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	/**
	 * MÉTODO para encontrar a un usuario a través de un email
	 * @param email
	 * @return usuario si existe email
	 */
	public User findByEmail(String email) {
		return this.userRepo.findByEmail(email).orElse(null);
	}
	
	public User findByUserName(String username) {
		return this.userRepo.findByUsername(username);
	}

	/**
	 * MÉTODO para encontrar a un usuario a través de su id
	 * @param id
	 * @return usuario que coincide con ese id
	 */
	public User findById(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	/**
	 * MÉTODO que hace una consulta para obtener un email de la tabla de usuarios
	 * @param email
	 * @return email si hay un usuario con ese email, null si no existe ningún email como ese en la tabla usuario
	 */
	public String getUserEmail(String email) {
		return this.userRepo.getEmail(email);
	}
	
	/**
	 * MÉTODO que hace una consulta para obtener la contraseña de un usuario a través de su email
	 * @param email
	 * @return password si ese email existe en la base de datos
	 */
	public String getUserPassword(String email) {
		return this.userRepo.getPassword(email);
	}
	
	/**
	 * MÉTODO para añadir un usuario a la base de datos
	 * @param user
	 * @return usuario que se acaba de añadir
	 */
	public User addUser(User user) {
		return this.userRepo.save(user);
	}
	
	
	
	

}
