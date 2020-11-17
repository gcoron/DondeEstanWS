package com.WebService.DondeEstanApp.controller;

import java.io.Serializable;
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
import com.WebService.DondeEstanApp.model.Arrival;
import com.WebService.DondeEstanApp.model.Location;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.AddressService;
import com.WebService.DondeEstanApp.service.ArrivalService;
import com.WebService.DondeEstanApp.service.LocationService;
import com.WebService.DondeEstanApp.service.UserObserveeService;
import com.WebService.DondeEstanApp.utils.ErrorCode;
import com.WebService.DondeEstanApp.utils.Paginator;
import com.WebService.DondeEstanApp.service.ObserverUserService;

@RestController
@RequestMapping(value = "/wsarr")
public class ArrivalController implements Serializable{

	private static final Logger logger = LoggerFactory.getLogger(ArrivalController.class);
	
	private static final long serialVersionUID = 1L;

	@Autowired
	ArrivalService arrivalService;
	
	@Autowired
	UserObserveeService userObserveeService;
	
	@Autowired
	ObserverUserService observerUserService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	LocationService locationService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/arrivals", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getListArrivals() {
		
		List<Arrival> arrivals;
		try {
			arrivals = arrivalService.getListArrival();
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();	
		for (Arrival arrival : arrivals) {
			JSONObject json = new JSONObject();
			json.put("dayHour", arrival.getDayHour());
			
			JSONObject jsonUserObservee = new JSONObject();
			jsonUserObservee.put("userId", arrival.getUserObservee().getUserId());
			jsonUserObservee.put("name", arrival.getUserObservee().getName());
			jsonUserObservee.put("lastName", arrival.getUserObservee().getLastName());
			jsonUserObservee.put("email", arrival.getUserObservee().getEmail());
			jsonUserObservee.put("companyName", arrival.getUserObservee().getCompanyName());
			jsonUserObservee.put("licensePlate", arrival.getUserObservee().getLicensePlate());
			jsonUserObservee.put("carRegistration", arrival.getUserObservee().getCarRegistration());
			json.put("userObservee", jsonUserObservee);
			
			JSONObject jsonObserverUser = new JSONObject();
			jsonObserverUser.put("userId", arrival.getObserverUser().getUserId());
			jsonObserverUser.put("name", arrival.getObserverUser().getName());
			jsonObserverUser.put("lastName", arrival.getObserverUser().getLastName());
			jsonObserverUser.put("email", arrival.getObserverUser().getEmail());
			json.put("observerUser", jsonObserverUser);
			
			JSONObject jsonAddress = new JSONObject();
			jsonAddress.put("addressId", arrival.getAddress().getAddressId());
			jsonAddress.put("street", arrival.getAddress().getStreet());
			jsonAddress.put("number", arrival.getAddress().getNumber());
			jsonAddress.put("floor", arrival.getAddress().getFloor());
			jsonAddress.put("apartament", arrival.getAddress().getApartament());
			jsonAddress.put("zipCode", arrival.getAddress().getZipCode());
			jsonAddress.put("city", arrival.getAddress().getCity());
			jsonAddress.put("state", arrival.getAddress().getState());
			jsonAddress.put("country", arrival.getAddress().getCountry());
			json.put("address", jsonAddress);
			
			JSONObject jsonLocation = new JSONObject();
			jsonLocation.put("locationId", arrival.getLocation().getLocationId());
			jsonLocation.put("latitude", arrival.getLocation().getLatitude());
			jsonLocation.put("longitude", arrival.getLocation().getLongitude());
			jsonLocation.put("dayHour", arrival.getLocation().getDayHour());
			json.put("location", jsonLocation);
			
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

	@RequestMapping(value = "/saveArrival", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String saveArrival(
			@RequestParam("dayHour") String dayHour, @RequestParam("observerUserId") int observerUserId,
			@RequestParam("userObserveeId") int userObserveeId, @RequestParam("addressId") int addressId,
			@RequestParam("locationId") int locationId) {

		UserObservee userObservee;
		ObserverUser observerUser;
		Address address;
		Location location;
		try {
			userObservee = userObserveeService.findUserObserveeById(userObserveeId);
			observerUser = observerUserService.findObserverUserById(observerUserId);
			address = addressService.findAddressById(addressId);
			location = locationService.findLocationById(locationId);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser != null) {
			if (userObservee != null) {
				if (address != null) {
					if (location != null) {
						Arrival arrival = new Arrival();
						arrival.setDayHour(dayHour);
						arrival.setObserverUser(observerUser);
						arrival.setUserObservee(userObservee);
						arrival.setAddress(address);
						arrival.setLocation(location);
						try {
							arrivalService.saveOrUpdate(arrival);
						} catch (Exception e) {
							ErrorCode errorCode = new ErrorCode(500, e.getMessage());
							logger.debug(errorCode.toString());
							return errorCode.toString();
						}
						JSONObject json = new JSONObject();
						json.put("arrivalId", arrival.getArrivalId());
						json.put("dayHour", arrival.getDayHour());
						
						JSONObject jsonUserObservee = new JSONObject();
						jsonUserObservee.put("userId", arrival.getUserObservee().getUserId());
						jsonUserObservee.put("name", arrival.getUserObservee().getName());
						jsonUserObservee.put("lastName", arrival.getUserObservee().getLastName());
						jsonUserObservee.put("email", arrival.getUserObservee().getEmail());
						jsonUserObservee.put("companyName", arrival.getUserObservee().getCompanyName());
						jsonUserObservee.put("licensePlate", arrival.getUserObservee().getLicensePlate());
						jsonUserObservee.put("carRegistration", arrival.getUserObservee().getCarRegistration());
						json.put("userObservee", jsonUserObservee);
						
						JSONObject jsonObserverUser = new JSONObject();
						jsonObserverUser.put("userId", arrival.getObserverUser().getUserId());
						jsonObserverUser.put("name", arrival.getObserverUser().getName());
						jsonObserverUser.put("lastName", arrival.getObserverUser().getLastName());
						jsonObserverUser.put("email", arrival.getObserverUser().getEmail());
						json.put("observerUser", jsonObserverUser);
						
						JSONObject jsonLocation = new JSONObject();
						jsonLocation.put("locationId", arrival.getLocation().getLocationId());
						jsonLocation.put("latitude", arrival.getLocation().getLatitude());
						jsonLocation.put("longitude", arrival.getLocation().getLongitude());
						jsonLocation.put("dayHour", arrival.getLocation().getDayHour());
						json.put("location", jsonLocation);
						
						JSONObject jsonAddress = new JSONObject();
						jsonAddress.put("addressId", arrival.getAddress().getAddressId());
						jsonAddress.put("street", arrival.getAddress().getStreet());
						jsonAddress.put("number", arrival.getAddress().getNumber());
						jsonAddress.put("floor", arrival.getAddress().getFloor());
						jsonAddress.put("apartament", arrival.getAddress().getApartament());
						jsonAddress.put("zipCode", arrival.getAddress().getZipCode());
						jsonAddress.put("city", arrival.getAddress().getCity());
						jsonAddress.put("state", arrival.getAddress().getState());
						jsonAddress.put("country", arrival.getAddress().getCountry());
						json.put("address", jsonAddress);
						
						data.put(json);
					} else {
						ErrorCode errorCode = new ErrorCode(500, "Incorrect location id");
						logger.debug(errorCode.toString());
						return errorCode.toString();
					}
				} else {
					ErrorCode errorCode = new ErrorCode(500, "Incorrect address id");
					logger.debug(errorCode.toString());
					return errorCode.toString();
				}
			} else {
				ErrorCode errorCode = new ErrorCode(500, "Incorrect user observee id");
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}	
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect observer user id");
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
	
	@RequestMapping(value = "/updateArrival", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String updateAddress(@RequestParam("id") int id, @RequestParam("dayHour") String dayHour) {
		
		Arrival arrival;
		try {
			arrival = arrivalService.findArrivalById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (arrival != null) {
			arrival.setArrivalId(id);
			arrival.setDayHour(dayHour);
			try {
				arrivalService.saveOrUpdate(arrival);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			JSONObject json = new JSONObject();
			json.put("arrivalId", arrival.getArrivalId());
			json.put("dayHour", arrival.getDayHour());
			
			JSONObject jsonUserObservee = new JSONObject();
			jsonUserObservee.put("userId", arrival.getUserObservee().getUserId());
			jsonUserObservee.put("name", arrival.getUserObservee().getName());
			jsonUserObservee.put("lastName", arrival.getUserObservee().getLastName());
			jsonUserObservee.put("email", arrival.getUserObservee().getEmail());
			jsonUserObservee.put("companyName", arrival.getUserObservee().getCompanyName());
			jsonUserObservee.put("licensePlate", arrival.getUserObservee().getLicensePlate());
			jsonUserObservee.put("carRegistration", arrival.getUserObservee().getCarRegistration());
			json.put("userObservee", jsonUserObservee);
			
			JSONObject jsonObserverUser = new JSONObject();
			jsonObserverUser.put("userId", arrival.getObserverUser().getUserId());
			jsonObserverUser.put("name", arrival.getObserverUser().getName());
			jsonObserverUser.put("lastName", arrival.getObserverUser().getLastName());
			jsonObserverUser.put("email", arrival.getObserverUser().getEmail());
			json.put("observerUser", jsonObserverUser);
			
			JSONObject jsonLocation = new JSONObject();
			jsonLocation.put("locationId", arrival.getLocation().getLocationId());
			jsonLocation.put("latitude", arrival.getLocation().getLatitude());
			jsonLocation.put("longitude", arrival.getLocation().getLongitude());
			jsonLocation.put("dayHour", arrival.getLocation().getDayHour());
			json.put("location", jsonLocation);
			
			JSONObject jsonAddress = new JSONObject();
			jsonAddress.put("addressId", arrival.getAddress().getAddressId());
			jsonAddress.put("street", arrival.getAddress().getStreet());
			jsonAddress.put("number", arrival.getAddress().getNumber());
			jsonAddress.put("floor", arrival.getAddress().getFloor());
			jsonAddress.put("apartament", arrival.getAddress().getApartament());
			jsonAddress.put("zipCode", arrival.getAddress().getZipCode());
			jsonAddress.put("city", arrival.getAddress().getCity());
			jsonAddress.put("state", arrival.getAddress().getState());
			jsonAddress.put("country", arrival.getAddress().getCountry());
			json.put("address", jsonAddress);
			
			data.put(json);
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect arrival id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}		
		
		response.put("code",  200);
		response.put("data",  data);
		Paginator paginator = new Paginator();
		response.put("paginator", paginator.PaginatorEmpty());
		response.put("status", "success");
		
		logger.debug(response.toString());
		return response.toString();	}

	@RequestMapping(value = "/deleteArrival", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody String deleteArrival(@RequestParam("id") int id) {
		
		Arrival arrival;
		try {
			arrival = arrivalService.findArrivalById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (arrival != null) {
			try {
				arrivalService.deleteArrival(id);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect arrival id");
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
