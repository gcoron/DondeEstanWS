package com.WebService.DondeEstanApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATIONS")
public class Notification {

	@Id
	@Column(name = "notificationId", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNotification;

	@Column(name = "title", nullable = true)
	private String title;

	@Column(name = "description", nullable = true)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userObserveeIdFK")
	private UserObservee userObservee;

	public int getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserObservee getUserObservee() {
		return userObservee;
	}

	public void setUserObservee(UserObservee userObservee) {
		this.userObservee = userObservee;
	}

	public Notification(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	public Notification() {
	}	
	
}
