package com.WebService.DondeEstanApp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebService.DondeEstanApp.dao.UserObserveeDAO;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.service.UserObserveeService;

@Service
@Transactional
public class UserObserveeServiceImpl implements UserObserveeService {

	private UserObserveeDAO userObserveeDAO;

	@Autowired
	public void setUserObserveeDao(UserObserveeDAO userObserveeDAO) {
		this.userObserveeDAO = userObserveeDAO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListUserObservee() {
		return userObserveeDAO.getListUserObservee();
	}

	@Override
	public void saveOrUpdate(UserObservee userObservee) {
		userObserveeDAO.saveOrUpdate(userObservee);
	}

	@Override
	public void deleteUserObservee(int id) {
		userObserveeDAO.deleteUserObservee(id);
	}

	@Override
	public UserObservee findUserObserveeById(int id) {
		return userObserveeDAO.findUserObserveeById(id);
	}

	@Override
	public UserObservee findUserObserveeByUsername(String username) {
		return userObserveeDAO.findUserObserveeByUsername(username);
	}
	
	@Override
	public UserObservee findUserObserveeByPrivacyKey(String privacyKey) {
		return userObserveeDAO.findUserObserveeByPrivacyKey(privacyKey);
	}
	
	@Override
	public UserObservee findUserObserveeByEmail(String email) {
		return userObserveeDAO.findUserObserveeByEmail(email);
	}
}