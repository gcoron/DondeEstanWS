package com.WebService.DondeEstanApp.model;

import java.io.Serializable;
//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ADDRESSES")
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "addressId", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	@Column(name = "street", nullable = true)
	private String street;
	
	@Column(name = "number", nullable = true)
	private double number;
	
	@Column(name = "zipCode", nullable = true)
	private long zipCode;
	
	@Column(name = "city", nullable = true)
	private String city;
	
	@Column(name = "state", nullable = true)
	private String state;
	
	@Column(name = "country", nullable = true)
	private String country;

	@Column(name = "floor")
	private String floor;
	
	@Column(name = "apartament")
	private String apartament;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "observerUserIdFK")
	private ObserverUser observerUser;
	
	public Address(String street, double number, long zipCode, String city, String state, String country,
			String floor, String apartament, String latitude, String longitude) {
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
		this.country = country;
		this.floor = floor;
		this.apartament = apartament;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Address() {
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getApartament() {
		return apartament;
	}

	public void setApartament(String apartament) {
		this.apartament = apartament;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public ObserverUser getObserverUser() {
		return observerUser;
	}

	public void setObserverUser(ObserverUser observerUser) {
		this.observerUser = observerUser;
	}

}
