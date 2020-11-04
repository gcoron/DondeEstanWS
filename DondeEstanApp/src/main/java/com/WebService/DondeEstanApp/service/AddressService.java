package com.WebService.DondeEstanApp.service;

import java.util.List;

import com.WebService.DondeEstanApp.model.Address;

public interface AddressService {

	@SuppressWarnings("rawtypes")
	public List getListAddress();

	public void saveOrUpdate(Address address);

	public void deleteAddress(int id);

	public Address findAddressById(int id);

}
