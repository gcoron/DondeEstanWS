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

@Entity
@Table(name = "LOCATIONS")
public class Location implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "locationId", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int locationId;

	@Column(name = "latitude", nullable = true)
	private String latitude;
	
	@Column(name = "longitude", nullable = true)
	private String longitude;
	
	@Column(name = "dayHour", nullable = true)
	private String dayHour;
	
	@ManyToOne
	@JoinColumn(name = "userObserveeIdFK")
	private UserObservee userObservee;

	public Location(String latitude, String longitude, String dayHour, UserObservee userObservee) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.dayHour = dayHour;
		this.userObservee = userObservee;
	}
	
	public Location() {
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
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

}
