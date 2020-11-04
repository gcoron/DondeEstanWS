package com.WebService.DondeEstanApp.controller;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.WebService.DondeEstanApp.model.Address;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.AddressService;
import com.WebService.DondeEstanApp.service.ObserverUserService;

@RestController
@RequestMapping(value = "/wsa")
public class AddressController {

	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	AddressService addressService;

	@Autowired
	ObserverUserService observerUserService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addresses", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List getListAddress() {
		List address = addressService.getListAddress();

		return address;
	}

	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public @ResponseBody Address addAddress(@RequestBody Address address) {
		addressService.saveOrUpdate(address);

		return address;
	}

	// http://localhost:8080/DondeEstanApp/wsa/saveAddress?street=25 de Mayo&number=2764&floor=11&apartament=A&zipCode=3000&city=Santa Fe&state=Santa Fe&country=Argentina&observerUserId=2
	@RequestMapping(value = "/saveAddress", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String saveAddress(
			@RequestParam("street") String street, @RequestParam("number") double number, 
			@RequestParam("floor") int floor, @RequestParam("apartament") String apartament, 
			@RequestParam("zipCode") long zipCode, @RequestParam("city") String city, 
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude,
			@RequestParam("observerUserId") int observerUserId) {
		
		ObserverUser observerUser = observerUserService.findObserverUserById(observerUserId);
		JSONObject response = new JSONObject();
		
		if (observerUser != null) {
			Address address = new Address();
			address.setStreet(street);
			address.setNumber(number);
			address.setFloor(floor);
			address.setApartament(apartament);
			address.setZipCode(zipCode);
			address.setCity(city);
			address.setState(state);
			address.setCountry(country);
			address.setLatitude(latitude);
			address.setLongitude(longitude);
			
			address.setObserverUser(observerUser);
			
			try {
				addressService.saveOrUpdate(address);
				response.put("success", Boolean.TRUE);
			} catch (Exception e) {
				response.put("error", e.getMessage());
			}
		} else {
			response.put("error", "Usuario Observador no encontrado.");
		}		
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/updateAddress", method = RequestMethod.PUT)
	public @ResponseBody String updateAddress(
			@RequestParam("id") int id, @RequestParam("street") String street, @RequestParam("number") double number, 
			@RequestParam("floor") int floor, @RequestParam("apartament") String apartament, 
			@RequestParam("zipCode") long zipCode, @RequestParam("city") String city, 
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude,
			@RequestParam("observerUserId") int observerUserId) {
		ObserverUser observerUser = observerUserService.findObserverUserById(observerUserId);
		JSONObject response = new JSONObject();
		
		if (observerUser != null) {
			Address address = new Address();
			address.setIdAddress(id);
			address.setStreet(street);
			address.setNumber(number);
			address.setFloor(floor);
			address.setApartament(apartament);
			address.setZipCode(zipCode);
			address.setCity(city);
			address.setState(state);
			address.setCountry(country);
			address.setLatitude(latitude);
			address.setLongitude(longitude);
			
			address.setObserverUser(observerUser);
			
			try {
				addressService.saveOrUpdate(address);
				response.put("success", Boolean.TRUE);
			} catch (Exception e) {
				response.put("error", e.getMessage());
			}
		} else {
			response.put("error", "Usuario Observador no encontrado.");
		}		
		
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/deleteAddress", method = RequestMethod.DELETE)
	public @ResponseBody Address deleteMessage(@RequestParam("id") int id) {
		Address address = addressService.findAddressById(id);
		addressService.deleteAddress(id);

		return address;
	}
}
