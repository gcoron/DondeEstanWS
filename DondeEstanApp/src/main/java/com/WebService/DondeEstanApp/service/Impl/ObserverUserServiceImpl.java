package com.WebService.DondeEstanApp.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebService.DondeEstanApp.dao.ObserverUserDAO;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.ObserverUserService;

@Service
@Transactional
public class ObserverUserServiceImpl implements ObserverUserService {

	private ObserverUserDAO observerUserDAO;

	@Autowired
	public void setObserverUserDao(ObserverUserDAO observerUserDAO) {
		this.observerUserDAO = observerUserDAO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListObserverUser() {
		return observerUserDAO.getListObserverUser();
	}

	@Override
	public void saveOrUpdate(ObserverUser observerUser) {
		observerUserDAO.saveOrUpdate(observerUser);
	}

	@Override
	public void deleteObserverUser(int id) {
		observerUserDAO.deleteObserverUser(id);
	}

	@Override
	public ObserverUser findObserverUserById(int id) {
		return observerUserDAO.findObserverUserById(id);
	}
	
	@Override
	public ObserverUser findObserverUserByUsername(String username) {
		return observerUserDAO.findObserverUserByUsername(username);
	}

	@Override
	public ObserverUser findObserverUserByEmail(String email) {
		return observerUserDAO.findObserverUserByEmail(email);
	}
}