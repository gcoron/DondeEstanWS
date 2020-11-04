package com.WebService.DondeEstanApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "userId", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "lastName", nullable = true)
	private String lastName;
	
	@Column(name = "numberId", nullable = true)
	private long numberId;

	@Column(name = "email", nullable = true, unique = true)
	private String email;
	
	@Column(name = "username", nullable = true, unique = true)
	private String username;

	@Column(name = "password", nullable = true)
	private String password;

	public User(String name, String lastName, long numberId, String email, String username, String password) {
		this.name = name;
		this.lastName = lastName;
		this.numberId = numberId;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getNumberId() {
		return numberId;
	}

	public void setNumberId(long numberId) {
		this.numberId = numberId;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [personId=" + userId + ", name=" + name + ", lastName=" + lastName + ", numberId=" + numberId
				+ ", email=" + email + "]";
	}
	
}