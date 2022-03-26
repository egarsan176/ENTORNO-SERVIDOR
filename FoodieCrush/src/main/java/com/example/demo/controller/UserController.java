package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;

/**
 * Esta clase es un controlador REST que intercepta peticiones al servidor,
 * encargándose de tareas relacionadas con el usuario
 * @author estefgar
 *
 */
@RestController
public class UserController {

    @Autowired private UserRepo userRepo;
    
    @Autowired private UserService userService;

    /**
     * MÉTODO que gestiona peticiones GET a /user y que te devuelve un usuario a través del token
     * @return usuario
     */
    @GetMapping("/user")
    public User getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(email).get();
    }


	/**
	 * MÉTODO que gestiona peticiones GET a /user/email para comprobar que un email existe en la base de datos --> http://localhost:9000/user/estefaniagarci@gmail.com
	 * @param email
	 * @return usuario si el email existe, null si no existe
	 */
	@GetMapping("/user/{email}")
	public User checkEmailUser(@PathVariable String email) {
		
		return this.userService.findByEmail(email);
	}
	
	/**
	 * MÉTODO que gestiona peticiones GET a /user/username para comprobar que un username existe en la base de datos --> http://localhost:9000/user/estefgar
	 * @param email
	 * @return usuario si el email existe, null si no existe
	 */
	@GetMapping("/user/checkUsername/{username}")
	public User checkUsernameUser(@PathVariable String username) {
		
		return this.userService.findByUserName(username);
	}


}