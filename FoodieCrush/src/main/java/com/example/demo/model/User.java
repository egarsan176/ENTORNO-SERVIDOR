package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	private String username;
	@Column(name="email", unique = true)
	private String email;
	//Evita que el campo password se incluya en el JSON de respuesta
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String role;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Notification> notifications;
	
	
	public User(String fullName, String username, String email, String password, String role) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.notifications = new ArrayList<>();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.notifications = new ArrayList<>();
	}
	
	public User(Long id) {
		this.id=id;
		this.notifications = new ArrayList<>();
	}

	public User(String fullName, String username, String email) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.notifications = new ArrayList<>();
	}

	public User() {
		super();
		this.notifications = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", role=" + role+"]";
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	

}
