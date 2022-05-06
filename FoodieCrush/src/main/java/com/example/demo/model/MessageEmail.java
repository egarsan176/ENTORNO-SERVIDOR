package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Esta clase se necesita para enviar un email
 * @author estefgar
 *
 */

public class MessageEmail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String message;
	private String fullName;

	
	public MessageEmail() {
		super();
	}
	

	public MessageEmail(String email, String message, String fullName) {
		super();
		this.email = email;
		this.message = message;
		this.fullName = fullName;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


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
		MessageEmail other = (MessageEmail) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "MessageEmail [id=" + id + ", email=" + email + ", message=" + message
				+ ", fullName=" + fullName + "]";
	}
	

	
	
	
}
