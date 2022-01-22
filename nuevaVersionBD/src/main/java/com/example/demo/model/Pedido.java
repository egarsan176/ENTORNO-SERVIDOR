package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	//PROPIEDADES
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //se genera una vez que haga save
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "envio")
	private String envio;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "costeTotalPedido")
	private double costeTotalPedido;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuario;
	
	@OneToMany
	@NotFound(action=NotFoundAction.IGNORE)
	private List<LineaPedido> listadoLineasPedido = new ArrayList<>();
	
	//CONSTRUCTOR
	
	public Pedido() {
		this.fecha = new Date();
	}
	
	public Pedido(Integer id) {
		super();
		this.id = id;	
	}
	
	//GETTERS Y SETTERS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public double getCosteTotalPedido() {
		return costeTotalPedido;
	}

	public void setCosteTotalPedido(double costeTotalPedido) {
		this.costeTotalPedido = costeTotalPedido;
	}
	public List<LineaPedido> getListadoLineasPedido() {
		return listadoLineasPedido;
	}

	public void setListadoLineasPedido(List<LineaPedido> listadoLineasPedido) {
		this.listadoLineasPedido = listadoLineasPedido;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//HASHCODE, EQUALS, TO STRING
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Pedido [Id=" + id + ", direccion=" + direccion + ", fecha=" + fecha + ", envio=" + envio + ", email="
				+ email + ", telefono=" + telefono + ", costeTotalPedido=" + costeTotalPedido + ", listadoLineasPedido="
				+ listadoLineasPedido + "]";
	}
	
	/**
	 * Este método muestra la fecha en un formato más entendible
	 * @return
	 */
	public String getFechaBonita() {
		return new SimpleDateFormat("dd-MM-yyyy || hh:mm:ss").format(this.fecha);
	}

}
