package com.WebService.DondeEstanApp.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.WebService.DondeEstanApp.dao.UserObserveeDAO;
import com.WebService.DondeEstanApp.model.User;
import com.WebService.DondeEstanApp.model.UserObservee;

@Repository
public class UserObserveeDAOImpl implements UserObserveeDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public List getListUserObservee() {
		Criteria criteria = getSession().createCriteria(UserObservee.class);

		return criteria.list();
	}

	@Override
	public void saveOrUpdate(UserObservee userObservee) {
		getSession().saveOrUpdate(userObservee);
	}

	@Override
	public void deleteUserObservee(int id) {
		UserObservee userObservee = getSession().get(UserObservee.class, id);
		getSession().delete(userObservee);
	}

	@Override
	public UserObservee findUserObserveeById(int id) {
		return getSession().get(UserObservee.class, id);
		
	}
	
	@Override
	public UserObservee findUserObserveeByUsername(String username) {
		User user = (User) getSession().createQuery("SELECT u FROM User u where u.username = :username").setParameter("username", username).uniqueResult();
		if (user!= null) {
			UserObservee uObservee = getSession().get(UserObservee.class, user.getUserId());
			return uObservee;
		} else {
			return null;
		}
	}
	
	@Override
	public UserObservee findUserObserveeByPrivacyKey(String privacyKey) {
		return (UserObservee) getSession().createQuery("SELECT u FROM UserObservee u where u.privacyKey = :privacyKey").setParameter("privacyKey", privacyKey).uniqueResult();
	}
	
	@Override
	public UserObservee findUserObserveeByEmail(String email) {
		User user = (User) getSession().createQuery("SELECT u FROM User u where u.email= :email").setParameter("email", email).uniqueResult();
		if (user!= null) {
			UserObservee uObservee = getSession().get(UserObservee.class, user.getUserId());
			return uObservee;
		} else {
			return null;
		}
	}
}
