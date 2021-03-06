package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

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

	/**
	 * MÉTODO para encontrar a un usuario a través de su id
	 * @param id
	 * @return usuario que coincide con ese id
	 */
	public User findById(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	
	

}
