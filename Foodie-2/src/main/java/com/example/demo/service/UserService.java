package com.example.demo.service;

import java.util.Optional;

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
	public Optional<User> findByEmail(String email) {
		return this.userRepo.findByEmail(email);
	}

	public User findById(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	
	

}
