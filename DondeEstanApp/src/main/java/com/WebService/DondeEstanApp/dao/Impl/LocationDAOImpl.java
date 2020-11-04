package com.WebService.DondeEstanApp.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.WebService.DondeEstanApp.dao.LocationDAO;
import com.WebService.DondeEstanApp.model.Location;

@Repository
public class LocationDAOImpl implements LocationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public List getListLocation() {
		Criteria criteria = getSession().createCriteria(Location.class);

		return criteria.list();
	}

	@Override
	public void saveOrUpdate(Location location) {
		getSession().saveOrUpdate(location);
	}

	@Override
	public void deleteLocation(int id) {
		Location location = getSession().get(Location.class, id);
		getSession().delete(location);
	}

	@Override
	public Location findLocationById(int id) {
		return getSession().get(Location.class, id);
	}

}
