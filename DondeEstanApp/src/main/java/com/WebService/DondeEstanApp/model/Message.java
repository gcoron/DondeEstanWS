package com.WebService.DondeEstanApp.model;

import java.io.Serializable;

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
@Table(name = "MESSAGES")
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "messageId", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messageId;

	@Column(name = "description", nullable = true)
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userObserveeIdFK")
	private UserObservee userObservee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "observerUserIdFK")
	private ObserverUser observerUser;

	public Message(String description, UserObservee userObservee, ObserverUser observerUser) {
		this.description = description;
		this.userObservee = userObservee;
		this.observerUser = observerUser;
	}

	public Message() {
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
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

	public ObserverUser getObserverUser() {
		return observerUser;
	}

	public void setObserverUser(ObserverUser observerUser) {
		this.observerUser = observerUser;
	}
	
	
}
