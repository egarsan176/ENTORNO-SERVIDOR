package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
   /**
    * MÉTODO para obtener un usuario por su email
    * @param email
    * @return usuario o null
    */
	public User findByEmail(String email);
	
	/**
	 * MÉTODO para obtener un usuario por su username
	 * @param username
	 * @return usuario o null
	 */
	public User findByUsername(String username);
	
	/**
	 * PARA EXCEPCIONES DEL LOGIN
	 * CONSULTA para obtener el email de un usuario a través de su email
	 * @param newEmail
	 * @return string email
	 */
	@Query(value="select email from user where email = ?1", nativeQuery = true) 
	public String getEmail(String newEmail);
	
}
	
