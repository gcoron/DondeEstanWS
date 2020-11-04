package com.WebService.DondeEstanApp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebService.DondeEstanApp.dao.AddressDAO;
import com.WebService.DondeEstanApp.model.Address;
import com.WebService.DondeEstanApp.service.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	private AddressDAO addressDAO;

	@Autowired
	public void setAddressDao(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListAddress() {
		return addressDAO.getListAddress();
	}

	@Override
	public void saveOrUpdate(Address address) {
		addressDAO.saveOrUpdate(address);
	}

	@Override
	public void deleteAddress(int id) {
		addressDAO.deleteAddress(id);
	}

	@Override
	public Address findAddressById(int id) {
		return addressDAO.findAddressById(id);
	}

}