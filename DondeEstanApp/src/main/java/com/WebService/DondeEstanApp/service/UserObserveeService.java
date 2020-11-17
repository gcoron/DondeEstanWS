package com.WebService.DondeEstanApp.service;

import java.util.List;

import com.WebService.DondeEstanApp.model.UserObservee;

public interface UserObserveeService {

	@SuppressWarnings("rawtypes")
	public List getListUserObservee();

	public void saveOrUpdate(UserObservee userObservee);

	public void deleteUserObservee(int id);
	
	public UserObservee findUserObserveeById(int id);

	public UserObservee findUserObserveeByUsername(String username);
	
	public UserObservee findUserObserveeByPrivacyKey(String privacyKey);

	public UserObservee findUserObserveeByEmail(String email);

}
