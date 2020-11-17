package com.WebService.DondeEstanApp.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.WebService.DondeEstanApp.model.Address;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.AddressService;
import com.WebService.DondeEstanApp.service.ObserverUserService;
import com.WebService.DondeEstanApp.utils.ErrorCode;
import com.WebService.DondeEstanApp.utils.Paginator;

@RestController
@RequestMapping(value = "/wsa")
public class AddressController {

	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	AddressService addressService;

	@Autowired
	ObserverUserService observerUserService;
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/addresses", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getListAddress() {
		
		List<Address> addresses;
		try {
			addresses = addressService.getListAddress();
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
			
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		for (Address address : addresses) {	
			JSONObject json = new JSONObject();
			json.put("addressId", address.getAddressId());
			json.put("street", address.getStreet());
			json.put("number", address.getNumber());
			json.put("floor", address.getFloor());
			json.put("apartament", address.getApartament());
			json.put("zipCode", address.getZipCode());
			json.put("city", address.getCity());
			json.put("state", address.getState());
			json.put("country", address.getCountry());
			
			JSONObject jsonObserverUser = new JSONObject();
			jsonObserverUser.put("userId", address.getObserverUser().getUserId());
			jsonObserverUser.put("name", address.getObserverUser().getName());
			jsonObserverUser.put("lastName", address.getObserverUser().getLastName());
			jsonObserverUser.put("email", address.getObserverUser().getEmail());
			json.put("observerUser", jsonObserverUser);
			
			data.put(json);
		}
		
		response.put("code",  200);
		response.put("data",  data);
		Paginator paginator = new Paginator();
		response.put("paginator", paginator.PaginatorEmpty());
		response.put("status", "success");
		
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/saveAddress", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String saveAddress(
			@RequestParam("street") String street, @RequestParam("number") double number, 
			@RequestParam(name = "floor", required = false) String floor, @RequestParam(name = "apartament", required = false) String apartament, 
			@RequestParam("zipCode") long zipCode, @RequestParam("city") String city, 
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude,
			@RequestParam("observerUserId") int observerUserId) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserById(observerUserId);
		} catch (Exception e){
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
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
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			JSONObject json= new JSONObject();
			json.put("addressId", address.getAddressId());
			json.put("street", address.getStreet());
			json.put("number", address.getNumber());
			json.put("floor", address.getFloor());
			json.put("apartament", address.getApartament());
			json.put("zipCode", address.getZipCode());
			json.put("city", address.getCity());
			json.put("state", address.getState());
			json.put("country", address.getCountry());
			
			JSONObject jsonObserverUser = new JSONObject();
			jsonObserverUser.put("userId", address.getObserverUser().getUserId());
			jsonObserverUser.put("name", address.getObserverUser().getName());
			jsonObserverUser.put("lastName", address.getObserverUser().getLastName());
			jsonObserverUser.put("email", address.getObserverUser().getEmail());
			json.put("observerUser", jsonObserverUser);
			
			data.put(json);
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}	
		
		response.put("code",  200);
		response.put("data",  data);
		Paginator paginator = new Paginator();
		response.put("paginator", paginator.PaginatorEmpty());
		response.put("status", "success");
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/updateAddress", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String updateAddress(
			@RequestParam("id") int id, @RequestParam("street") String street, @RequestParam("number") double number, 
			@RequestParam("floor") String floor, @RequestParam("apartament") String apartament, 
			@RequestParam("zipCode") long zipCode, @RequestParam("city") String city, 
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude) {
		
		Address address;
		try {
			address = addressService.findAddressById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (address != null) {
			address.setAddressId(id);
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
			
			try {
				addressService.saveOrUpdate(address);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			JSONObject json= new JSONObject();
			json.put("addressId", address.getAddressId());
			json.put("street", address.getStreet());
			json.put("number", address.getNumber());
			json.put("floor", address.getFloor());
			json.put("apartament", address.getApartament());
			json.put("zipCode", address.getZipCode());
			json.put("city", address.getCity());
			json.put("state", address.getState());
			json.put("country", address.getCountry());
			
			JSONObject jsonObserverUser = new JSONObject();
			jsonObserverUser.put("userId", address.getObserverUser().getUserId());
			jsonObserverUser.put("name", address.getObserverUser().getName());
			jsonObserverUser.put("lastName", address.getObserverUser().getLastName());
			jsonObserverUser.put("email", address.getObserverUser().getEmail());
			json.put("observerUser", jsonObserverUser);
			
			data.put(json);
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect address id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}		
		
		response.put("code",  200);
		response.put("data",  data);
		Paginator paginator = new Paginator();
		response.put("paginator", paginator.PaginatorEmpty());
		response.put("status", "success");
		
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/deleteAddress", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody String deleteMessage(@RequestParam("id") int id) {
		
		Address address;
		try {
			address = addressService.findAddressById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (address != null) {
			try {
				addressService.deleteAddress(id);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect address id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		response.put("code",  200);
		response.put("data",  data);
		Paginator paginator = new Paginator();
		response.put("paginator", paginator.PaginatorEmpty());
		response.put("status", "success");
		
		logger.debug(response.toString());
		return response.toString();
	}
}
