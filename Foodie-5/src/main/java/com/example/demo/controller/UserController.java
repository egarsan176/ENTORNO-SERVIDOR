package com.example.demo.controller;


import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NotificationNotFoundException;
import com.example.demo.model.MessageEmail;
import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.EmailSMTP;
import com.example.demo.service.NotificationService;
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
    @Autowired private NotificationService notificationService;
    
    @Autowired private EmailSMTP sendEmail;

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
	
	/**
	 * MÉTODO que gestiona peticiones DELETE a users/notifications/id pa borrar uu¡na notificación de la lista de notificaciones del usuario
	 * @param id
	 * @return
	 */
	@DeleteMapping("users/notifications/{id}")
	public ResponseEntity<?> deleteNotification(@PathVariable Integer id){
		
		Notification notif = this.notificationService.getNotificationByID(id);
		
		if(notif!=null) {
		
		this.notificationService.delete(notif);
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = this.userService.findByEmail(email);
		this.userRepo.save(user);
		return ResponseEntity.noContent().build();
		}
		throw new NotificationNotFoundException(id);
		
	}
	
  
	/**
	 * A este método se accede cuando un usuario rellena el formulario de contacto y se envia un email a la cuenta de correo de FoodieCrush
	 * @param datos
	 * @throws MessagingException
	 */
    @PostMapping("/contactUs")
    public void sendEmail(@RequestBody MessageEmail datos) throws MessagingException {
    	
		this.sendEmail.sendSimpleMessage(datos, "FoodieHELP! - "+datos.getFullName()+" ha enviado un mensaje!");
	}


}