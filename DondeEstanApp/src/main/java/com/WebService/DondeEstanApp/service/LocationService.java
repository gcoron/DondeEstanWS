package com.WebService.DondeEstanApp.service;

import java.util.List;

import com.WebService.DondeEstanApp.model.Location;

public interface LocationService {

	@SuppressWarnings("rawtypes")
	public List getListLocation();

	public void saveOrUpdate(Location location);

	public void deleteLocation(int id);

	public Location findLocationById(int id);

}
