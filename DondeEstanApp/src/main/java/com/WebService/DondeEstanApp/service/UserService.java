package com.WebService.DondeEstanApp.service;

import java.util.List;

import com.WebService.DondeEstanApp.model.User;

public interface UserService {

	@SuppressWarnings("rawtypes")
	public List getListUser();

	public void saveOrUpdate(User user);

	public void deleteUser(int id);

	public User findUserById(int id);

}