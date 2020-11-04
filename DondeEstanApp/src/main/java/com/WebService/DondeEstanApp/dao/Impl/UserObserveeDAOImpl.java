package com.WebService.DondeEstanApp.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.WebService.DondeEstanApp.dao.UserObserveeDAO;
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
	public JSONObject findOnlyUserObserveeById(int id) {
		UserObservee uObservado = getSession().get(UserObservee.class, id);
		JSONObject response = new JSONObject();
		
		response.put("name", uObservado.getName());
		response.put("lastname", uObservado.getLastName());
		response.put("username", uObservado.getUsername());
		response.put("password", uObservado.getPassword());
		response.put("companyName", uObservado.getCompanyName());
		response.put("patente", uObservado.getLicensePlate());
		response.put("matricula", uObservado.getCarRegistration());
		response.put("privacyKey", uObservado.getPrivacyKey());
		
		return response;
	}
	
	@Override
	public UserObservee findUserObserveeByUsername(String username) {
		return (UserObservee) getSession().createQuery("SELECT u FROM UsuarioObservado u where u.nombreUsuario = :username").setParameter("username", username).uniqueResult();
	}
	
	@Override
	public UserObservee findUserObserveeByPrivacyKey(String clavePrivacidad) {
		return (UserObservee) getSession().createQuery("SELECT u FROM UsuarioObservado u where u.clavePrivacidad = :clavePrivacidad").setParameter("clavePrivacidad", clavePrivacidad).uniqueResult();
	}
	
	@Override
	public UserObservee findUserObserveeByEmail(String email) {
		return (UserObservee) getSession().createQuery("SELECT u FROM UsuarioObservado u where u.email= :email").setParameter("email", email).uniqueResult();
	}
}
