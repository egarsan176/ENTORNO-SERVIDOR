package com.example.demo.controller;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.EmailNotFoundException;
import com.example.demo.exception.PasswordNotFoundException;
import com.example.demo.model.LoginCredentials;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.JWTUtil;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController //para habilitar esta clase como un controlador REST y que pueda interceptar peticiones al servidor.
public class AuthController {

    @Autowired private UserRepo userRepo;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/register")
    public Map<String, Object> registerHandler(@RequestBody User user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user = userRepo.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return Collections.singletonMap("access_token", token);
    }

    @PostMapping("/auth/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("access_token", token);
        }catch (AuthenticationException authExc){
   
        	//email existe pero no es su contraseña
        	if(this.userRepo.getEmail(body.getEmail()) !=  null && !Objects.equals(this.userRepo.getPassword(body.getEmail()), body.getPassword())) {
        		throw new PasswordNotFoundException();
        	
        	}
        	//email no existe
        	else {
        		throw new EmailNotFoundException();
        		
        	}
        }
    }
    
    
    
    ////////////////////////////////////////GESTIÓN DE EXCEPCIONES
    
	/**
	 * GESTIÓN DE EXCEPCIÓN EmailNotFoundException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si el email no se encuentra 
	 */
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ApiError> handleEmailNoEncontrado(EmailNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * GESTIÓN DE EXCEPCIÓN PasswordNotFoundException
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción si la password no se encuentra 
	 */
	@ExceptionHandler(PasswordNotFoundException.class)
	public ResponseEntity<ApiError> handlePasswordNoEncontrado(PasswordNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * GESTIÓN DE EXCEPCIÓN DE JSON MAL FORMADO
	 * @param ex
	 * @return un json con el estado, fecha, hora y mensaje de la excepción --> ignora la traza de la excepción
	 */
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.BAD_REQUEST);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	
	
    
    /**
	 * A este método se accede para ver si el usuario tiene un token válido o no
	 * Es necesario para que el guardián de acceso a /publicar
	 * @return token o error
	 * @throws Exception
	 */
	@GetMapping("/login")
	public ResponseEntity<String> comprobarTokenValido() throws Exception {
		
		try {
			return ResponseEntity.ok("");
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	


}
