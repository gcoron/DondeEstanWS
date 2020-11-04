package com.WebService.DondeEstanApp.dao;

import java.util.List;

import com.WebService.DondeEstanApp.model.Location;

public interface LocationDAO {

	@SuppressWarnings("rawtypes")
	public List getListLocation();

	public void saveOrUpdate(Location location);

	public void deleteLocation(int id);

	public Location findLocationById(int id);

}
