package com.WebService.DondeEstanApp.dao;

import java.util.List;

import com.WebService.DondeEstanApp.model.ObserverUser;

public interface ObserverUserDAO {

	@SuppressWarnings("rawtypes")
	public List getListObserverUser();

	public void saveOrUpdate(ObserverUser observerUser);

	public void deleteObserverUser(int id);

	public ObserverUser findObserverUserById(int id);

	public ObserverUser findObserverUserByUsername(String username);

	public ObserverUser findObserverUserByEmail(String email);
}
