package com.WebService.DondeEstanApp.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.WebService.DondeEstanApp.dao.AddressDAO;
import com.WebService.DondeEstanApp.model.Address;

@Repository
public class AddressDAOImpl implements AddressDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public List getListAddress() {
		Criteria criteria = getSession().createCriteria(Address.class);

		return criteria.list();
	}

	@Override
	public void saveOrUpdate(Address address) {
		getSession().saveOrUpdate(address);
	}

	@Override
	public void deleteAddress(int id) {
		Address address = getSession().get(Address.class, id);
		getSession().delete(address);
	}

	@Override
	public Address findAddressById(int id) {
		return getSession().get(Address.class, id);
	}

}

