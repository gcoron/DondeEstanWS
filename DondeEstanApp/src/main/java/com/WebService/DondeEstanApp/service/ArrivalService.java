package com.WebService.DondeEstanApp.service;

import java.util.List;

import com.WebService.DondeEstanApp.model.Arrival;

public interface ArrivalService {

	@SuppressWarnings("rawtypes")
	public List getListArrival();

	public void saveOrUpdate(Arrival arrival);

	public void deleteArrival(int id);

	public Arrival findArrivalById(int id);

}
