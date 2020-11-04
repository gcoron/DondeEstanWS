package com.WebService.DondeEstanApp.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.WebService.DondeEstanApp.dao.NotificationDAO;
import com.WebService.DondeEstanApp.model.Notification;

@Repository
public class NotificationDAOImpl implements NotificationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public List getListNotification() {
		Criteria criteria = getSession().createCriteria(Notification.class);

		return criteria.list();
	}

	@Override
	public void saveOrUpdate(Notification notification) {
		getSession().saveOrUpdate(notification);
	}

	@Override
	public void deleteNotification(int id) {
		Notification notification = getSession().get(Notification.class, id);
		getSession().delete(notification);
	}

	@Override
	public Notification findNotificationById(int id) {
		return getSession().get(Notification.class, id);
	}

}

