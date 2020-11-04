package com.WebService.DondeEstanApp.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.WebService.DondeEstanApp.dao.ArrivalDAO;
import com.WebService.DondeEstanApp.model.Arrival;

@Repository
public class ArrivalDAOImpl implements ArrivalDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public List getListArrival() {
		Criteria criteria = getSession().createCriteria(Arrival.class);

		return criteria.list();
	}

	@Override
	public void saveOrUpdate(Arrival arrival) {
		getSession().saveOrUpdate(arrival);
	}

	@Override
	public void deleteArrival(int id) {
		Arrival arrival = getSession().get(Arrival.class, id);
		getSession().delete(arrival);
	}

	@Override
	public Arrival findArrivalById(int id) {
		return getSession().get(Arrival.class, id);
	}

}
