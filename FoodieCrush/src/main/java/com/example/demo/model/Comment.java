package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String message;
	@ManyToOne
	private User user;
	@OneToOne
	private Recipe recipe;
	private boolean isPending;
	
	
	public Comment(String message) {
		super();
		this.message = message;
		this.isPending = false;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public boolean isPending() {
		return isPending;
	}


	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}


	@Override
	public String toString() {
		return "Comment [id=" + id + ", message=" + message + ", user=" + user + ", recipe=" + recipe + ", isPending="
				+ isPending + "]";
	}



	
	
	
	

}
