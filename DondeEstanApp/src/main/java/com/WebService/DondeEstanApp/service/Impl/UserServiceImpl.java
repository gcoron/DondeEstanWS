package com.WebService.DondeEstanApp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebService.DondeEstanApp.dao.UserDAO;
import com.WebService.DondeEstanApp.model.User;
import com.WebService.DondeEstanApp.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Autowired
	public void setUserDao(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListUser() {
		return userDAO.getListUser();
	}

	@Override
	public void saveOrUpdate(User user) {
		userDAO.saveOrUpdate(user);
	}

	@Override
	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}

	@Override
	public User findUserById(int id) {
		return userDAO.findUserById(id);
	}

}