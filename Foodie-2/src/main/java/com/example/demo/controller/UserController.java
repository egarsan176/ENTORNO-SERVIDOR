package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;


@RestController
public class UserController {

    @Autowired private UserRepo userRepo;
    
    @Autowired private UserService userService;

//    @GetMapping("/user")
//    public User getUserDetails(){
//        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return userRepo.findByEmail(email)).get();
//    }
    /**
     * MÉTODO para encontrar el id de un usuario a través de su nombre de usuario
     * @return
     */
	@GetMapping("/user")
	public ResponseEntity<Long> getUserDetails() {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(this.userRepo.findByUsername(username).getId());
	}


	/**
	 * MÉTODO para comprobar que un email existe en la base de datos
	 * @param email
	 * @return usuario si el email existe, null si no existe
	 */
	@GetMapping("/user/{email}")
	public User checkEmailUser(@PathVariable String email) {
		
		return this.userService.findByEmail(email);
	}


}