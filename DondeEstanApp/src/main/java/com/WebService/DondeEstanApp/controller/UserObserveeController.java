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

import com.WebService.DondeEstanApp.model.Arrival;
import com.WebService.DondeEstanApp.model.Location;
import com.WebService.DondeEstanApp.model.Message;
import com.WebService.DondeEstanApp.model.Notification;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.UserObserveeService;
import com.WebService.DondeEstanApp.utils.ErrorCode;
import com.WebService.DondeEstanApp.utils.Paginator;

@RestController
@RequestMapping(value = "/wsuo")
public class UserObserveeController {

	private static final Logger logger = LoggerFactory.getLogger(UserObserveeController.class);
	
	@Autowired
	UserObserveeService userObserveeService;

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/usersObservees", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getListUserObservee() {
		
		List<UserObservee> usersObservees;
		try {
			usersObservees = userObserveeService.getListUserObservee();
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		for (UserObservee userObservee : usersObservees) {
			JSONObject json = new JSONObject();
			json.put("userId", userObservee.getUserId());
			json.put("name", userObservee.getName());
			json.put("lastName", userObservee.getLastName());
			json.put("numberId", userObservee.getNumberId());
			json.put("email", userObservee.getEmail());
			json.put("username", userObservee.getUsername());
			json.put("password", userObservee.getPassword());
			json.put("companyName", userObservee.getCompanyName());
			json.put("licensePlate", userObservee.getLicensePlate());
			json.put("carRegistration", userObservee.getCarRegistration());
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

	@RequestMapping(value = "/userObserveeById", method = RequestMethod.GET, headers = "Accept=application/json")//produces = "text/plain;charset=UTF-8")//
	public @ResponseBody String findUserObserveeById(@RequestParam("id") int id) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeById(id);
		} catch (Exception e){
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject json = new JSONObject();
		if (userObservee != null) {
			json.put("userId", userObservee.getUserId());
			json.put("name", userObservee.getName());
			json.put("lastName", userObservee.getLastName());
			json.put("numberId", userObservee.getNumberId());
			json.put("email", userObservee.getEmail());
			json.put("username", userObservee.getUsername());
			json.put("password", userObservee.getPassword());
			json.put("privacyKey", userObservee.getPrivacyKey());
			json.put("companyName", userObservee.getCompanyName());
			json.put("licensePlate", userObservee.getLicensePlate());
			json.put("carRegistration", userObservee.getCarRegistration());
			data.put(json);
			
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/saveUserObservee", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String saveUserObservee(@RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("numberId") long numberId, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("companyName") String companyName, @RequestParam("licensePlate") String licensePlate, @RequestParam("carRegistration") String carRegistration) {
		
		UserObservee userObservee = new UserObservee();
		userObservee.setName(name);
		userObservee.setLastName(lastName);
		userObservee.setNumberId(numberId);
		userObservee.setEmail(email);
		userObservee.setPrivacyKey(username + "." + numberId);
		userObservee.setUsername(username);
		userObservee.setPassword(password);
		userObservee.setCompanyName(companyName);
		userObservee.setLicensePlate(licensePlate);
		userObservee.setCarRegistration(carRegistration);
		
		UserObservee userByUsername;
		UserObservee userByEmail;
		try {
			userByUsername = userObserveeService.findUserObserveeByUsername(username);
			userByEmail = userObserveeService.findUserObserveeByEmail(email);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		if (userByUsername != null) {
			ErrorCode errorCode = new ErrorCode(500, "User already exists");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		} 
		if (userByEmail != null) {
			ErrorCode errorCode = new ErrorCode(500, "Email already exists");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		try {
			userObserveeService.saveOrUpdate(userObservee);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("userId", userObservee.getUserId());
		json.put("name", userObservee.getName());
		json.put("lastName", userObservee.getLastName());
		json.put("numberId", userObservee.getNumberId());
		json.put("username", userObservee.getUsername());
		json.put("password", userObservee.getPassword());
		json.put("companyName", userObservee.getCompanyName());
		json.put("licensePlate", userObservee.getLicensePlate());
		json.put("carRegistration", userObservee.getCarRegistration());
		json.put("privacyKey", userObservee.getPrivacyKey());
		data.put(json);
		
		response.put("code",  200);
		response.put("data",  data);
		Paginator paginator = new Paginator();
		response.put("paginator", paginator.PaginatorEmpty());
		response.put("status", "success");
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeByUsername", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeByUsername(@RequestParam("username") String username) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeByUsername(username);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (userObservee != null) {
			JSONObject json = new JSONObject();
			json.put("userId", userObservee.getUserId());
			json.put("name", userObservee.getName());
			json.put("lastName", userObservee.getLastName());
			json.put("numberId", userObservee.getNumberId());
			json.put("email", userObservee.getEmail());
			json.put("username", userObservee.getUsername());
			json.put("password", userObservee.getPassword());
			json.put("privacyKey", userObservee.getPrivacyKey());
			json.put("companyName", userObservee.getCompanyName());
			json.put("licensePlate", userObservee.getLicensePlate());
			json.put("carRegistration", userObservee.getCarRegistration());
			data.put(json);
			
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect username");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeByPrivacyKey", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeByPrivacyKey(@RequestParam("privacyKey") String privacyKey) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeByPrivacyKey(privacyKey);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (userObservee != null) {
			JSONObject json = new JSONObject();
			json.put("userId", userObservee.getUserId());
			json.put("name", userObservee.getName());
			json.put("lastName", userObservee.getLastName());
			json.put("numberId", userObservee.getNumberId());
			json.put("email", userObservee.getEmail());
			json.put("username", userObservee.getUsername());
			json.put("password", userObservee.getPassword());
			json.put("privacyKey", userObservee.getPrivacyKey());
			json.put("companyName", userObservee.getCompanyName());
			json.put("licensePlate", userObservee.getLicensePlate());
			json.put("carRegistration", userObservee.getCarRegistration());
			data.put(json);
			
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");	
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect privacy key");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/userObserveeByEmail", method = RequestMethod.GET, headers = "Accept=application/json")//, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeByEmail(@RequestParam("email") String email) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeByEmail(email);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (userObservee != null) {
			JSONObject json = new JSONObject();
			json.put("userId", userObservee.getUserId());
			json.put("name", userObservee.getName());
			json.put("lastName", userObservee.getLastName());
			json.put("numberId", userObservee.getNumberId());
			json.put("email", userObservee.getEmail());
			json.put("username", userObservee.getUsername());
			json.put("password", userObservee.getPassword());
			json.put("privacyKey", userObservee.getPrivacyKey());
			json.put("companyName", userObservee.getCompanyName());
			json.put("licensePlate", userObservee.getLicensePlate());
			json.put("carRegistration", userObservee.getCarRegistration());
			data.put(json);
			
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");	
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect email");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/listObserversUsersOfUserObservee", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUsersObserversByIdUserObservee(@RequestParam("id") int id) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (userObservee != null) {
			List<ObserverUser> observers = (List<ObserverUser>) userObservee.getObservadores();
			
			for (ObserverUser observer : observers) {
				JSONObject json = new JSONObject();
				json.put("id", observer.getUserId());
				json.put("name", observer.getName());
				json.put("lastName", observer.getLastName());
				json.put("email", observer.getEmail());
				json.put("childsName", observer.getChildsName());
				data.put(json);
			}
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/updateUserObservee", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String update(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("numberId") long numberId, @RequestParam("password") String password, @RequestParam("companyName") String companyName, @RequestParam("licensePlate") String licensePlate, @RequestParam("carRegistration") String carRegistration) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		if (userObservee != null) {
			userObservee.setUserId(id);
			userObservee.setName(name);
			userObservee.setLastName(lastName);
			userObservee.setNumberId(numberId);
			userObservee.setPassword(password);
			userObservee.setCompanyName(companyName);
			userObservee.setLicensePlate(licensePlate);
			userObservee.setCarRegistration(carRegistration);
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		try {
			userObserveeService.saveOrUpdate(userObservee);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("userId", userObservee.getUserId());
		json.put("name", userObservee.getName());
		json.put("lastName", userObservee.getLastName());
		json.put("numberId", userObservee.getNumberId());
		json.put("companyName", userObservee.getCompanyName());
		json.put("licensePlate", userObservee.getLicensePlate());
		json.put("carRegistration", userObservee.getCarRegistration());
		json.put("privacyKey", userObservee.getPrivacyKey());
		data.put(json);
		
		response.put("code",  200);
		response.put("data",  data);
		Paginator paginator = new Paginator();
		response.put("paginator", paginator.PaginatorEmpty());
		response.put("status", "success");
		
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/deleteUserObservee", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody String delete(@RequestParam("id") int id) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (userObservee != null) {
			try {
				userObserveeService.deleteUserObservee(id);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");
			
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeNotifications", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeNotifications(@RequestParam("id") int id) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (userObservee != null) {
			List<Notification> notifications = (List<Notification>) userObservee.getNotifications();
			
			for (Notification notification : notifications) {
				JSONObject json = new JSONObject();
				json.put("id", notification.getTitle());
				json.put("description", notification.getDescription());
				data.put(json);
			}
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeMessages", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeMessages(@RequestParam("id") int id) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (userObservee != null) {
			List<Message> messages = (List<Message>) userObservee.getUserObserveeMessages();
			
			for (Message message : messages) {
				JSONObject json = new JSONObject();
				json.put("description", message.getDescription());
				json.put("observerUser", message.getObserverUser().getName() + " " + message.getObserverUser().getLastName());
				json.put("userObservee", message.getUserObservee().getName() + " " + message.getUserObservee().getLastName());
				data.put(json);
			}
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeLocations", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeLocations(@RequestParam("id") int id) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (userObservee != null) {
			List<Location> locations = (List<Location>) userObservee.getUserObserveeLocation();
			
			for (Location location : locations) {
				JSONObject json = new JSONObject();
				json.put("latitude", location.getLatitude());
				json.put("longitude", location.getLongitude());
				json.put("dayHour", location.getDayHour());
				data.put(json);
			}
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeArrivals", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeArrivals(@RequestParam("id") int id) {
		
		UserObservee userObservee;
		try {
			userObservee = userObserveeService.findUserObserveeById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		
		if (userObservee != null) {
			List<Arrival> arrivals = (List<Arrival>) userObservee.getUserObserveeArrival();
			
			for (Arrival arrival : arrivals) {
				JSONObject json = new JSONObject();
				JSONObject jsonUserObservee = new JSONObject();
				JSONObject jsonObserverUser = new JSONObject();
				JSONObject jsonAddress = new JSONObject();
				JSONObject jsonLocation = new JSONObject();
				
				json.put("dayHour", arrival.getDayHour());
				
				jsonUserObservee.put("id", arrival.getUserObservee().getUserId());
				jsonUserObservee.put("name", arrival.getUserObservee().getName());
				jsonUserObservee.put("lastName", arrival.getUserObservee().getLastName());
				json.put("userObservee", jsonUserObservee);
				
				jsonObserverUser.put("id", arrival.getObserverUser().getUserId());
				jsonObserverUser.put("name", arrival.getObserverUser().getName());
				jsonObserverUser.put("lastName", arrival.getObserverUser().getLastName());
				json.put("observerUser", jsonObserverUser);
				
				jsonAddress.put("id", arrival.getAddress().getAddressId());
				jsonAddress.put("street", arrival.getAddress().getStreet());
				jsonAddress.put("number", arrival.getAddress().getNumber());
				jsonAddress.put("floor", arrival.getAddress().getFloor());
				jsonAddress.put("apartament", arrival.getAddress().getApartament());
				jsonAddress.put("zipCode", arrival.getAddress().getZipCode());
				jsonAddress.put("city", arrival.getAddress().getCity());
				jsonAddress.put("state", arrival.getAddress().getState());
				jsonAddress.put("country", arrival.getAddress().getCountry());
				jsonAddress.put("latitude", arrival.getAddress().getLatitude());
				jsonAddress.put("longitude", arrival.getAddress().getLongitude());
				json.put("address", jsonAddress);
				
				jsonLocation.put("id", arrival.getLocation().getLocationId());
				jsonLocation.put("latitude", arrival.getLocation().getLatitude());
				jsonLocation.put("longitude", arrival.getLocation().getLongitude());
				json.put("location", jsonLocation);
				
				data.put(json);
			}
			response.put("code",  200);
			response.put("data",  data);
			Paginator paginator = new Paginator();
			response.put("paginator", paginator.PaginatorEmpty());
			response.put("status", "success");
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
}