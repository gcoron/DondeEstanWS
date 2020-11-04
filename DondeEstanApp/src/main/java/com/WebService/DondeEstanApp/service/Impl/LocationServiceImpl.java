package com.WebService.DondeEstanApp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebService.DondeEstanApp.dao.LocationDAO;
import com.WebService.DondeEstanApp.model.Location;
import com.WebService.DondeEstanApp.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	private LocationDAO locationDAO;

	@Autowired
	public void setAddressDao(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListLocation() {
		return locationDAO.getListLocation();
	}

	@Override
	public void saveOrUpdate(Location location) {
		locationDAO.saveOrUpdate(location);
	}

	@Override
	public void deleteLocation(int id) {
		locationDAO.deleteLocation(id);
	}

	@Override
	public Location findLocationById(int id) {
		return locationDAO.findLocationById(id);
	}

}