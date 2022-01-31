package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	//PROPIEDADES
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "userName", nullable = false)
	private String userName;
	
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;
	
	@Column(name = "dni", nullable = false)
	private String dni;
	
	@Column(name = "password", nullable = false)
	private String password;
	
//	// ¡¡IMPORTANTE!!
//	
//	//En @OneToMany el fetch type default es Lazy, esto hace que el atributo pedidos no sea instanciado hasta que se haga getPedidos().
//	//Con @OneToMany(fetch=FetchType.EAGER) instanciamos pedidos junto con el resto de los atributos.
//	@OneToMany(fetch=FetchType.EAGER)
//	private List<Pedido> pedidos = new ArrayList<>();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//CONSTRUCTOR
	public Usuario() {}

	public Usuario(String nombre,String userName, String email, String telefono, String direccion, String dni, String password) {
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

//	public List<Pedido> getPedidos() {
//		return pedidos;
//	}
//
//	public void setPedidos(List<Pedido> pedidos) {
//		this.pedidos = pedidos;
//	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	//HASHCODE, EQUALS Y TO STRING


	@Override
	public int hashCode() {
		return Objects.hash(direccion, dni, email, nombre, password, telefono, userName);
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
				&& Objects.equals(password, other.password)
				&& Objects.equals(telefono, other.telefono) && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", userName=" + userName + ", email=" + email + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", dni=" + dni + ", password=" + password + "]";
	}

	
//	/**
//	 * Este método añade un pedido al usuario siempre al principio de la lista de pedidos
//	 *  (para que estén ordenados por fecha)
//	 * @param pedido
//	 */
//	public void addPedido(Pedido pedido) {
//		this.pedidos.add(0, pedido);
//		
//	}

}
