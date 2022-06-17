package com.example.demo.model;

/**
 * Clase DTO LoginCredentials
 * Crea un objeto LoginCredential que sirve para hacer login
 * @author estefgar
 *
 */
public class LoginCredentials {

    private String email;
    private String password;
    
	public LoginCredentials() {
		super();
	}
	public LoginCredentials(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginCredentials [email=" + email + ", password=" + password + "]";
	}
	
	
    
    

}