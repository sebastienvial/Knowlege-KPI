package com.bobst.knowledge.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KPI_ROLE {
	
	@Id
	private String email;
	private String password;
	private String role;
	
	public KPI_ROLE(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
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
	
	

}
