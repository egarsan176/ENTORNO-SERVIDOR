package com.example.demo.model;

public class DatosPedido {
	
	private String envio;
	private String email;
	private String telefono;
	private String direccion;
	
	
	
	public DatosPedido(String envio, String email, String telefono, String direccion) {
		super();
		this.envio = envio;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	
	public String getEnvio() {
		return envio;
	}
	public void setEnvio(String envio) {
		this.envio = envio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	

}
