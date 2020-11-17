package com.WebService.DondeEstanApp.model;

import java.io.Serializable;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "ARRIVALS")
public class Arrival implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "arrivalId", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int arrivalId;
	
	@Column(name = "dayHour", nullable = true)
	private String dayHour;

	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userObserveeIdFK")
	private UserObservee userObservee;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "observerUserIdFK")
	private ObserverUser observerUser;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "addressIdFK")
	private Address address;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "locationIdFK")
	private Location location;
	
	public Arrival(String dayHour, UserObservee userObservee, ObserverUser observerUser, Location location,
			Address address) {
		this.dayHour = dayHour;
		this.userObservee = userObservee;
		this.observerUser = observerUser;
		this.location = location;
		this.address = address;
	}
	
	public Arrival() {
	}

	public int getArrivalId() {
		return arrivalId;
	}

	public void setArrivalId(int arrivalId) {
		this.arrivalId = arrivalId;
	}

	public String getDayHour() {
		return dayHour;
	}

	public void setDayHour(String dayHour) {
		this.dayHour = dayHour;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
