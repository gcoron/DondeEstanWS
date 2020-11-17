package com.WebService.DondeEstanApp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USERS_OBSERVEES")
@PrimaryKeyJoinColumn(referencedColumnName = "userId")
public class UserObservee extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "privacyKey", nullable = true, unique = true)
	private String privacyKey;

	@Column(name = "companyName", nullable = true)
	private String companyName;

	@Column(name = "licensePlate", nullable = true)
	private String licensePlate;
	
	@Column(name = "carRegistration", nullable = true)
	private String carRegistration;
	
	@JsonIgnore
	@OneToMany(mappedBy="userObservee", fetch = FetchType.EAGER)
	private List<ObserverUser> observers;
	
	@JsonIgnore
	@OneToMany(mappedBy="userObservee", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Notification> notifications;
	
	@JsonIgnore
	@OneToMany(mappedBy="userObservee", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Message> userObserveeMessages;

	@JsonIgnore
	@OneToMany(mappedBy="userObservee", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Location> userObserveeLocations;
		
	@JsonIgnore
	@OneToMany(mappedBy="userObservee", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Arrival> userObserveeArrival;
	
	public UserObservee() {
	}

	public UserObservee(
		String name, String lastName, long numberId, String email,
		String privacyKey, String username, String password, 
		String companyName, String licensePlate, String carRegistration) {
			super(name, lastName, numberId, email, username, password);
			this.privacyKey = privacyKey;
			this.companyName = companyName;
			this.licensePlate = licensePlate;
			this.carRegistration = carRegistration;
	}

	@Override
	public int getUserId() {
		return super.getUserId();
	}

	@Override
	public void setUserId(int id) {
		super.setUserId(id);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}
	
	@Override
	public String getLastName() {
		return super.getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		super.setLastName(lastName);
	}
	
	@Override
	public long getNumberId() {
		return super.getNumberId();
	}

	@Override
	public void setNumberId(long numberId) {
		super.setNumberId(numberId);
	}
	
	@Override
	public String getEmail() {
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		super.setEmail(email);
	}
	
	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public void setUsername(String username) {
		super.setUsername(username);
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public void setPassword(String password) {
		super.setPassword(password);
	}
	
	public String getPrivacyKey() {
		return privacyKey;
	}

	public void setPrivacyKey(String privacyKey) {
		this.privacyKey = privacyKey;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getCarRegistration() {
		return carRegistration;
	}

	public void setCarRegistration(String carRegistration) {
		this.carRegistration = carRegistration;
	}
	
	public List<ObserverUser> getObservadores() {
		return observers;
	}

	public void setObservadores(List<ObserverUser> observers) {
		this.observers = observers;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<Message> getUserObserveeMessages() {
		return userObserveeMessages;
	}

	public void setUserObserveeMessages(List<Message> userObserveeMessages) {
		this.userObserveeMessages = userObserveeMessages;
	}
	
	public List<Location> getUserObserveeLocation() {
		return userObserveeLocations;
	}

	public void setUserObserveeLocation(List<Location> userObserveeLocation) {
		this.userObserveeLocations = userObserveeLocation;
	}
	
	public List<Arrival> getUserObserveeArrival() {
		return userObserveeArrival;
	}

	public void setUserObserveeArrival(List<Arrival> userObserveeArrival) {
		this.userObserveeArrival = userObserveeArrival;
	}

	public List<Location> getUserObserveeLocations() {
		return userObserveeLocations;
	}

	public void setUserObserveeLocations(List<Location> userObserveeLocations) {
		this.userObserveeLocations = userObserveeLocations;
	}

	@Override
	public String toString() {
		return "UserObservee [privacyKey=" + privacyKey	+ ", companyName=" + companyName 
				+ ", licensePlate=" + licensePlate + ", carRegistration=" + carRegistration
				+ ", observers=" + observers + ", notifications=" + notifications 
				+ ", userObserveeMessages="	+ userObserveeMessages + ", userObserveeLocations=" 
				+ userObserveeLocations + ", userObserveeArrival=" + userObserveeArrival + "]";
	}
	
	
	
}