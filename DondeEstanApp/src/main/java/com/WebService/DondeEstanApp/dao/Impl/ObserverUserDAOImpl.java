package com.WebService.DondeEstanApp.dao.Impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.WebService.DondeEstanApp.dao.ObserverUserDAO;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.model.User;

@Repository
public class ObserverUserDAOImpl implements ObserverUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public List getListObserverUser() {
		Criteria criteria = getSession().createCriteria(ObserverUser.class);

		return criteria.list();
	}

	@Override
	public void saveOrUpdate(ObserverUser observerUser) {
		getSession().saveOrUpdate(observerUser);
	}

	@Override
	public void deleteObserverUser(int id) {
		ObserverUser observerUser = getSession().get(ObserverUser.class, id);
		getSession().delete(observerUser);
	}

	@Override
	public ObserverUser findObserverUserById(int id) {
		return getSession().get(ObserverUser.class, id);
	}

	@Override
	public ObserverUser findObserverUserByUsername(String username) {
		User user = (User) getSession().createQuery("SELECT u FROM User u where u.username = :username").setParameter("username", username).uniqueResult();
		if (user!= null) {
			ObserverUser uObserver = getSession().get(ObserverUser.class, user.getUserId());
			return uObserver;
		} else {
			return null;
		}
	}

	@Override
	public ObserverUser findObserverUserByEmail(String email) {
		User user = (User) getSession().createQuery("SELECT u FROM User u where u.email = :email").setParameter("email", email).uniqueResult();
		if (user!= null) {
			ObserverUser uObserver = getSession().get(ObserverUser.class, user.getUserId());
			return uObserver;
		} else {
			return null;
		}
	}
}

