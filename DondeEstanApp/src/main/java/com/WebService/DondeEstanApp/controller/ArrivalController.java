package com.WebService.DondeEstanApp.controller;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;
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
import com.WebService.DondeEstanApp.model.Arrival;
import com.WebService.DondeEstanApp.model.Location;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.AddressService;
import com.WebService.DondeEstanApp.service.ArrivalService;
import com.WebService.DondeEstanApp.service.LocationService;
import com.WebService.DondeEstanApp.service.UserObserveeService;
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
	
	@RequestMapping(value = "/arrivals", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getListArrivals() {
		@SuppressWarnings("unchecked")
		List<Arrival> arrivals = arrivalService.getListArrival();
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
			
		for (Arrival arrival : arrivals) {
			JSONObject json = new JSONObject();
			json.put("dayHour", arrival.getDayHour());
			json.put("nameUserObservee", arrival.getUserObservee().getName() + " " + arrival.getUserObservee().getLastName());
			json.put("nameObserverUser", arrival.getObserverUser().getName() + " " + arrival.getObserverUser().getLastName());
			json.put("address", arrival.getAddress().getStreet() + " " + arrival.getAddress().getNumber() + ", Latitude: " + arrival.getAddress().getLatitude() + ", Longitude: " + arrival.getAddress().getLongitude());
			json.put("locationUserObservee", arrival.getLocation().getLatitude() + ", " + arrival.getLocation().getLongitude());
			responseArray.put(json);
		}
		response.put("arrivals", responseArray);
		
		logger.debug(response.toString());
		return responseArray.toString();
	}

	@RequestMapping(value = "/addArrival", method = RequestMethod.POST)
	public @ResponseBody Arrival addArrival(@RequestBody Arrival arrival) {
		arrivalService.saveOrUpdate(arrival);

		return arrival;
	}

	// 
	@RequestMapping(value = "/saveArrival", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String saveArrival(
			@RequestParam("dayHour") String dayHour, @RequestParam("observerUserId") int observerUserId,
			@RequestParam("userObserveeId") int userObserveeId, @RequestParam("addressId") int addressId,
			@RequestParam("locationId") int locationId) {
		
		ObserverUser observerUser = observerUserService.findObserverUserById(observerUserId);
		UserObservee userObservee = userObserveeService.findUserObserveeById(userObserveeId);
		Address address = addressService.findAddressById(addressId);
		Location location = locationService.findLocationById(locationId);
		
		JSONObject response = new JSONObject();
		
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
							response.put("exito", Boolean.TRUE);
						} catch (Exception e) {
							response.put("error", e.getMessage());
						}
					} else {
						response.put("error", "Locacion no encontrada.");
					}
				} else {
					response.put("error", "Dirección no encontrada.");
				}
			} else {
				response.put("error", "Usuario Observado no encontrado.");
			}	
		} else {
			response.put("error", "Usuario Observador no encontrado.");
		}		
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/updateArrival", method = RequestMethod.PUT)
	public @ResponseBody String updateAddress(@RequestParam("id") int id,
			@RequestParam("dayHour") String dayHour, @RequestParam("observerUserId") int observerUserId,
			@RequestParam("userObserveeId") int userObserveeId, @RequestParam("addressId") int addressId,
			@RequestParam("locationId") int locationId) {
		
		ObserverUser observerUser = observerUserService.findObserverUserById(observerUserId);
		UserObservee userObservee = userObserveeService.findUserObserveeById(userObserveeId);
		Address address = addressService.findAddressById(addressId);
		Location location = locationService.findLocationById(locationId);
		
		JSONObject response = new JSONObject();
		
		if (observerUser != null) {
			if (userObservee != null) {
				if (address != null) {
					if (location != null) {
						Arrival arrival = new Arrival();
						arrival.setIdArrival(id);
						arrival.setDayHour(dayHour);
						arrival.setObserverUser(observerUser);
						arrival.setUserObservee(userObservee);
						arrival.setAddress(address);
						arrival.setLocation(location);
						
						try {
							arrivalService.saveOrUpdate(arrival);
							response.put("exito", Boolean.TRUE);
						} catch (Exception e) {
							response.put("error", e.getMessage());
						}
					} else {
						response.put("error", "Locacion no encontrada.");
					}
				} else {
					response.put("error", "Dirección no encontrada.");
				}
			} else {
				response.put("error", "Usuario Observado no encontrado.");
			}	
		} else {
			response.put("error", "Usuario Observador no encontrado.");
		}		
		
		logger.debug(response.toString());
		return response.toString();	}

	@RequestMapping(value = "/deleteArrival", method = RequestMethod.DELETE)
	public @ResponseBody Arrival deleteArrival(@RequestParam("id") int id) {
		Arrival arrival = arrivalService.findArrivalById(id);
		arrivalService.deleteArrival(id);

		return arrival;
	}

}
