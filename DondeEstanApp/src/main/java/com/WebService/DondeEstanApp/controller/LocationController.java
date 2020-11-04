package com.WebService.DondeEstanApp.controller;

import java.io.Serializable;
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

import com.WebService.DondeEstanApp.model.Location;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.service.LocationService;
import com.WebService.DondeEstanApp.service.UserObserveeService;

@RestController
@RequestMapping(value = "/wsloc")
public class LocationController implements Serializable{

	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserObserveeService userObserveeService;
	
	@Autowired
	LocationService locationService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/locations", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List getListArrivals() {
		List locations = locationService.getListLocation();

		return locations;
	}

	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
	public @ResponseBody Location addLocation(@RequestBody Location location) {
		locationService.saveOrUpdate(location);

		return location;
	}

	// http://localhost:8080/DondeEstanApp/wsa/saveAddress?street=25 de Mayo&number=2764&floor=11&apartament=A&zipCode=3000&city=Santa Fe&state=Santa Fe&country=Argentina&observerUserId=2
	@RequestMapping(value = "/saveLocation", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String saveLocation(
			@RequestParam("latitude") String latitude,@RequestParam("longitude") String longitude,
			@RequestParam("dayHour") String dayHour, @RequestParam("userObeservee") int userObserveeId) {
		
		UserObservee userObservee = userObserveeService.findUserObserveeById(userObserveeId);
		
		JSONObject response = new JSONObject();
		
		if (userObservee != null) {
			Location location = new Location();
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setDayHour(dayHour);
			location.setUserObservee(userObservee);
			
			try {
				locationService.saveOrUpdate(location);
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
	
	@RequestMapping(value = "/updateLocation", method = RequestMethod.PUT)
	public @ResponseBody String updateLocation(@RequestParam("id") int id,
			@RequestParam("latitude") String latitude,@RequestParam("longitude") String longitude,
			@RequestParam("dayHour") String dayHour, @RequestParam("userObeservee") int userObserveeId) {
		
		UserObservee userObservee = userObserveeService.findUserObserveeById(userObserveeId);
		
		JSONObject response = new JSONObject();
		
		if (userObservee != null) {
			Location location = new Location();
			location.setLocationId(id);
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setDayHour(dayHour);
			location.setUserObservee(userObservee);
			
			try {
				locationService.saveOrUpdate(location);
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

	@RequestMapping(value = "/deleteLocation", method = RequestMethod.DELETE)
	public @ResponseBody Location deleteLocation(@RequestParam("id") int id) {
		Location location = locationService.findLocationById(id);
		locationService.deleteLocation(id);

		return location;
	}
	
}

