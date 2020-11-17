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

import com.WebService.DondeEstanApp.model.Location;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.service.LocationService;
import com.WebService.DondeEstanApp.service.UserObserveeService;
import com.WebService.DondeEstanApp.utils.ErrorCode;
import com.WebService.DondeEstanApp.utils.Paginator;

@RestController
@RequestMapping(value = "/wsloc")
public class LocationController implements Serializable{

	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserObserveeService userObserveeService;
	
	@Autowired
	LocationService locationService;
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/locations", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getListArrivals() {
		
		List<Location> locations;
		try {
			locations = locationService.getListLocation();
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		for (Location location : locations) {
			JSONObject json = new JSONObject();
			json.put("locationId", location.getLocationId());
			json.put("latitude", location.getLatitude());
			json.put("longitude", location.getLongitude());
			json.put("dayHour", location.getDayHour());
			JSONObject jsonUserObservee = new JSONObject();
			jsonUserObservee.put("userId", location.getUserObservee().getUserId());
			jsonUserObservee.put("name", location.getUserObservee().getName());
			jsonUserObservee.put("lastName", location.getUserObservee().getLastName());
			jsonUserObservee.put("email", location.getUserObservee().getEmail());
			jsonUserObservee.put("companyName", location.getUserObservee().getCompanyName());
			jsonUserObservee.put("licensePlate", location.getUserObservee().getLicensePlate());
			jsonUserObservee.put("carRegistration", location.getUserObservee().getCarRegistration());
			json.put("userObservee", jsonUserObservee);
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

	@RequestMapping(value = "/saveLocation", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String saveLocation(
			@RequestParam("latitude") String latitude,@RequestParam("longitude") String longitude,
			@RequestParam("dayHour") String dayHour, @RequestParam("userObservee") int userObserveeId) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeById(userObserveeId);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		
		if (userObservee != null) {
			Location location = new Location();
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setDayHour(dayHour);
			location.setUserObservee(userObservee);
			try {
				locationService.saveOrUpdate(location);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			JSONObject json = new JSONObject();
			json.put("locationId", location.getLocationId());
			json.put("latitude", location.getLatitude());
			json.put("longitude", location.getLongitude());
			json.put("dayHour", location.getDayHour());
			JSONObject jsonUserObservee = new JSONObject();
			jsonUserObservee.put("userId", location.getUserObservee().getUserId());
			jsonUserObservee.put("name", location.getUserObservee().getName());
			jsonUserObservee.put("lastName", location.getUserObservee().getLastName());
			jsonUserObservee.put("email", location.getUserObservee().getEmail());
			jsonUserObservee.put("companyName", location.getUserObservee().getCompanyName());
			jsonUserObservee.put("licensePlate", location.getUserObservee().getLicensePlate());
			jsonUserObservee.put("carRegistration", location.getUserObservee().getCarRegistration());
			json.put("userObservee", jsonUserObservee);
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
	
	@RequestMapping(value = "/updateLocation", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String updateLocation(@RequestParam("id") int id,
			@RequestParam("latitude") String latitude,@RequestParam("longitude") String longitude,
			@RequestParam("dayHour") String dayHour) {
		
		Location location;
		try {
			location = locationService.findLocationById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		
		if (location != null) {
			location.setLocationId(id);
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setDayHour(dayHour);
			try {
				locationService.saveOrUpdate(location);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			JSONObject json = new JSONObject();
			json.put("locationId", location.getLocationId());
			json.put("latitude", location.getLatitude());
			json.put("longitude", location.getLongitude());
			json.put("dayHour", location.getDayHour());
			JSONObject jsonUserObservee = new JSONObject();
			jsonUserObservee.put("userId", location.getUserObservee().getUserId());
			jsonUserObservee.put("name", location.getUserObservee().getName());
			jsonUserObservee.put("lastName", location.getUserObservee().getLastName());
			jsonUserObservee.put("email", location.getUserObservee().getEmail());
			jsonUserObservee.put("companyName", location.getUserObservee().getCompanyName());
			jsonUserObservee.put("licensePlate", location.getUserObservee().getLicensePlate());
			jsonUserObservee.put("carRegistration", location.getUserObservee().getCarRegistration());
			json.put("userObservee", jsonUserObservee);
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

	@RequestMapping(value = "/deleteLocation", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody String deleteLocation(@RequestParam("id") int id) {
		
		Location location;
		try {
			location = locationService.findLocationById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (location != null) {
			try {
				locationService.deleteLocation(id);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect location id");
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

