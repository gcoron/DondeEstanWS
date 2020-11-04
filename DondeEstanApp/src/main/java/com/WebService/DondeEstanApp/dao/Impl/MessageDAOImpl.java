package com.WebService.DondeEstanApp.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.WebService.DondeEstanApp.dao.MessageDAO;
import com.WebService.DondeEstanApp.model.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public List getListMessage() {
		Criteria criteria = getSession().createCriteria(Message.class);

		return criteria.list();
	}

	@Override
	public void saveOrUpdate(Message message) {
		getSession().saveOrUpdate(message);
	}

	@Override
	public void deleteMessage(int id) {
		Message message = getSession().get(Message.class, id);
		getSession().delete(message);
	}

	@Override
	public Message findMessageById(int id) {
		return getSession().get(Message.class, id);
	}

}
