package com.WebService.DondeEstanApp.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.WebService.DondeEstanApp.dao.UserDAO;
import com.WebService.DondeEstanApp.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public List getListUser() {
		Criteria criteria = getSession().createCriteria(User.class);

		return criteria.list();
	}

	@Override
	public void saveOrUpdate(User user) {
		getSession().saveOrUpdate(user);
	}

	@Override
	public void deleteUser(int id) {
		User user = getSession().get(User.class, id);
		getSession().delete(user);
	}

	@Override
	public User findUserById(int id) {
		return getSession().get(User.class, id);
	}

}
