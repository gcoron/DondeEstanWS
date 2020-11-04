package com.WebService.DondeEstanApp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebService.DondeEstanApp.dao.ArrivalDAO;
import com.WebService.DondeEstanApp.model.Arrival;
import com.WebService.DondeEstanApp.service.ArrivalService;

@Service
@Transactional
public class ArrivalServiceImpl implements ArrivalService {

	private ArrivalDAO arrivalDAO;

	@Autowired
	public void setArrivalDao(ArrivalDAO arrivalDAO) {
		this.arrivalDAO = arrivalDAO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListArrival() {
		return arrivalDAO.getListArrival();
	}

	@Override
	public void saveOrUpdate(Arrival arrival) {
		arrivalDAO.saveOrUpdate(arrival);
	}

	@Override
	public void deleteArrival(int id) {
		arrivalDAO.deleteArrival(id);
	}

	@Override
	public Arrival findArrivalById(int id) {
		return arrivalDAO.findArrivalById(id);
	}

}
