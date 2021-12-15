package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

//v2
public class Usuario {

	//PROPIEDADES
	@NotEmpty
	private String nombre;
	@NotEmpty(message="El usuario no puede estar vacío") 
	private String userName;
	@Email
	private String email;
	private String telefono;
	private String direccion;
	private String dni;
	@Min(6)
	private String password;
	private List<Pedido> pedidos = new ArrayList<>();
	
	//CONSTRUCTOR
	public Usuario() {}

	public Usuario(String nombre,String userName, @Email String email, String telefono, String direccion, String dni, @Min(6) String password) {
		super();
		this.nombre = nombre;
		this.userName = userName;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.dni = dni;
		this.password = password;
	}

	public Usuario(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public Usuario(String userName) {
		super();
		this.userName = userName;
	}
	
	//GETTERS Y SETTERS
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	//HASHCODE, EQUALS Y TO STRING

	@Override
	public int hashCode() {
		return Objects.hash(direccion, dni, email, nombre, password, pedidos, telefono, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(direccion, other.direccion) && Objects.equals(dni, other.dni)
				&& Objects.equals(email, other.email) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(pedidos, other.pedidos)
				&& Objects.equals(telefono, other.telefono) && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", userName=" + userName + ", email=" + email + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", dni=" + dni + ", password=" + password + ", pedidos=" + pedidos + "]";
	}

	
	/**
	 * Este método añade un pedido al usuario siempre al principio de la lista de pedidos
	 *  (para que estén ordenados por fecha)
	 * @param pedido
	 */
	public void addPedido(Pedido pedido) {
		this.pedidos.add(0, pedido);
		
		
	}
	
	
	
	
}
