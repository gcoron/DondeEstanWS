package com.WebService.DondeEstanApp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "OBSERVERS_USERS")
@PrimaryKeyJoinColumn(referencedColumnName = "observerUserIdFK")
public class ObserverUser extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "childsName", nullable = true)
	private String childsName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userObserveeIdFK")
	private UserObservee userObservee;

	@JsonIgnore
	@OneToMany(mappedBy="observerUser", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Message> observerUserMessages;
	
	@JsonIgnore
	@OneToMany(mappedBy="observerUser", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Address> observerUserAddress;
	
	@JsonIgnore
	@OneToMany(mappedBy="observerUser", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Arrival> observerUserArrival;

	public ObserverUser() {
	}

	public ObserverUser(
		String name, String lastName, long numberId, String email,
		String username, String password, String nameHijx) {
			super(name, lastName, numberId, email, username, password);
			this.childsName = nameHijx;
	}
	
	@Override
	public void setUserId(int id) {
		super.setUserId(id);
	}
	
	@Override
	public int getUserId() {
		return super.getUserId();
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

	public String getChildsName() {
		return childsName;
	}

	public void setChildsName(String childsName) {
		this.childsName = childsName;
	}
	
	public UserObservee getDriver() {
		return userObservee;
	}

	public void setDriver(UserObservee driver) {
		this.userObservee = driver;
	}
	
	public List<Message> getObserverUserMessages() {
		return observerUserMessages;
	}

	public void setObserverUserMessages(List<Message> observerUserMessages) {
		this.observerUserMessages = observerUserMessages;
	}
	
	public List<Address> getObserverUserAddress() {
		return observerUserAddress;
	}

	public void setObserverUserAddress(List<Address> observerUserAddress) {
		this.observerUserAddress = observerUserAddress;
	}
	
	public List<Arrival> getObserverUserArrival() {
		return observerUserArrival;
	}

	public void setObserverUserArrival(List<Arrival> observerUserArrival) {
		this.observerUserArrival = observerUserArrival;
	}

	@Override
	public String toString() {
		return "ObserverUser [nameHijx=" + childsName
				+ ", userObservee=" + userObservee + ", observerUserMessages=" + observerUserMessages
				+ ", observerUserAddress=" + observerUserAddress + ", observerUserArrival=" + observerUserArrival + "]";
	}

	
	
	
}