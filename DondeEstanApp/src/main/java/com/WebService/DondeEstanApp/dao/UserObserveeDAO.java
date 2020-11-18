package com.WebService.DondeEstanApp.dao;

import java.util.List;

import com.WebService.DondeEstanApp.model.UserObservee;

public interface UserObserveeDAO {

	@SuppressWarnings("rawtypes")
	public List getListUserObservee();

	public void saveOrUpdate(UserObservee userObservee);

	public void deleteUserObservee(int id);

	public UserObservee findUserObserveeById(int id);

	public UserObservee findUserObserveeByUsername(String username);

	public UserObservee findUserObserveeByPrivacyKey(String privacyKey);

	public UserObservee findUserObserveeByEmail(String email);
}
