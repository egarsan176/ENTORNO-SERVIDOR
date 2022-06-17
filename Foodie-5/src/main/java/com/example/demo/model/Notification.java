package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Clase Notification. Entidad.
 * Crea el objeto notificación cuyos atributos son:
 * id, title, message, user, fecha.
 * @author estefgar
 *
 */
@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String message;
	@JsonIgnore
	@ManyToOne
	private User user;
	@JsonIgnore
	private Date fecha;
	
	
	public Notification() {
		super();
		this.fecha = new Date();
	}
	
	public Notification(String message) {
		super();
		this.fecha = new Date();
	}
	
	/**
	 * Este método muestra la fecha en un formato más entendible
	 * @return
	 */
	public String getFechaBonita() {
		return new SimpleDateFormat("dd-MM-yyyy || hh:mm:ss").format(this.fecha);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", title=" + title + ", message=" + message + ", user=" + user + ", fecha="
				+ this.getFechaBonita()+ "]";
	}
	
	
	

	
}
