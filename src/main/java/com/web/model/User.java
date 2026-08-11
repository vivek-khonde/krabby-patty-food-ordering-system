package com.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fullName;
	private int phone;
	private String email;
	private String password;
	private String role;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String fullName, int phone, String email, String password, String role) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", email=" + email + ", password="
				+ password + ", role=" + role + "]";
	}

}
