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
import com.WebService.DondeEstanApp.model.Arrival;
import com.WebService.DondeEstanApp.model.Message;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.ObserverUserService;
import com.WebService.DondeEstanApp.service.UserObserveeService;
import com.WebService.DondeEstanApp.utils.ErrorCode;
import com.WebService.DondeEstanApp.utils.Paginator;

@RestController
@RequestMapping(value = "/wsuor")
public class ObserverUserController {

	private static final Logger logger = LoggerFactory.getLogger(ObserverUserController.class);
	
	@Autowired
	ObserverUserService observerUserService;
	
	@Autowired
	UserObserveeService userObserveeService;

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/observerUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getListUsuarioObservador() {
		
		List<ObserverUser> observersUsers;
		try {
			observersUsers = observerUserService.getListObserverUser();
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
			
		for (ObserverUser observerUser : observersUsers) {
			JSONObject json = new JSONObject();
			json.put("userId", observerUser.getUserId());
			json.put("name", observerUser.getName());
			json.put("lastName", observerUser.getLastName());
			json.put("numberId", observerUser.getNumberId());
			json.put("email", observerUser.getEmail());
			json.put("username", observerUser.getUsername());
			json.put("password", observerUser.getPassword());
			json.put("childsName", observerUser.getChildsName());
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

	@RequestMapping(value = "/observerUserById", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findObserverUserById(@RequestParam("id") int id) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser != null) {
			JSONObject json = new JSONObject();
			json.put("userId", observerUser.getUserId());
			json.put("name", observerUser.getName());
			json.put("lastName", observerUser.getLastName());
			json.put("numberId", observerUser.getNumberId());
			json.put("email", observerUser.getEmail());
			json.put("username", observerUser.getUsername());
			json.put("password", observerUser.getPassword());
			json.put("childsName", observerUser.getChildsName());
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
	
	@RequestMapping(value = "/observerUserByUsername", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeByUsername(@RequestParam("username") String username) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserByUsername(username);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser != null) {
			JSONObject json = new JSONObject();
			json.put("userId", observerUser.getUserId());
			json.put("name", observerUser.getName());
			json.put("lastName", observerUser.getLastName());
			json.put("numberId", observerUser.getNumberId());
			json.put("email", observerUser.getEmail());
			json.put("username", observerUser.getUsername());
			json.put("password", observerUser.getPassword());
			json.put("childsName", observerUser.getChildsName());
			data.put(json);
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect username");
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
	
	@RequestMapping(value = "/observerUserByEmail", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUsuarioObservadorByEmail(@RequestParam("email") String email) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserByEmail(email);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser != null) {
			JSONObject json = new JSONObject();
			json.put("userId", observerUser.getUserId());
			json.put("name", observerUser.getName());
			json.put("lastName", observerUser.getLastName());
			json.put("numberId", observerUser.getNumberId());
			json.put("email", observerUser.getEmail());
			json.put("username", observerUser.getUsername());
			json.put("password", observerUser.getPassword());
			json.put("childsName", observerUser.getChildsName());
			data.put(json);
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect email");
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
	
	@RequestMapping(value = "/saveObserverUser", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String saveObserverUser(@RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("numberId") long numberId, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("childsName") String childsName, @RequestParam("userObserveePrivacyKey") String userObserveePrivacyKey) {
		
		ObserverUser userByUsername;
		ObserverUser userByEmail;
		UserObservee userObservee;
		try {
			userByUsername = observerUserService.findObserverUserByUsername(username);
			userByEmail = observerUserService.findObserverUserByEmail(email);
			userObservee = userObserveeService.findUserObserveeByPrivacyKey(userObserveePrivacyKey);
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
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (userObservee != null) {
			ObserverUser observerUser = new ObserverUser();
			observerUser.setName(name);
			observerUser.setLastName(lastName);
			observerUser.setNumberId(numberId);
			observerUser.setEmail(email);
			observerUser.setUsername(username);
			observerUser.setPassword(password);
			observerUser.setChildsName(childsName);
			observerUser.setDriver(userObservee);
			try {
				observerUserService.saveOrUpdate(observerUser);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			
			JSONObject json = new JSONObject();
			json.put("userId", observerUser.getUserId());
			json.put("name", observerUser.getName());
			json.put("lastName", observerUser.getLastName());
			json.put("numberId", observerUser.getNumberId());
			json.put("username", observerUser.getUsername());
			json.put("password", observerUser.getPassword());
			json.put("childsName", observerUser.getChildsName());
			data.put(json);
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect privacy key");
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
	
	@RequestMapping(value = "/saveDriverInObserverUser", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String saveDriverInObserverUser(@RequestParam("id") int id, @RequestParam("privacyKey") String privacyKey) {
		
		ObserverUser observerUser = new ObserverUser();
		ObserverUser observer;
		UserObservee observee;
		try {
			observer = observerUserService.findObserverUserById(id);
			observee = userObserveeService.findUserObserveeByPrivacyKey(privacyKey);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observer != null) {
			if (observee != null) {
				observerUser.setUserId(observer.getUserId());
				observerUser.setName(observer.getName());
				observerUser.setLastName(observer.getLastName());
				observerUser.setNumberId(observer.getNumberId());
				observerUser.setEmail(observer.getEmail());
				observerUser.setUsername(observer.getUsername());
				observerUser.setPassword(observer.getPassword());
				observerUser.setChildsName(observer.getChildsName());
				observerUser.setDriver(observee);
				
				try {
					observerUserService.saveOrUpdate(observerUser);
				} catch (Exception e) {
					ErrorCode errorCode = new ErrorCode(500, e.getMessage());
					logger.debug(errorCode.toString());
					return errorCode.toString();
				}
			} else {
				ErrorCode errorCode = new ErrorCode(500, "Incorrect privacy key");
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
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
	
	@RequestMapping(value = "/updateObserverUser", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String update(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("numberId") long numberId,  @RequestParam("password") String password, @RequestParam("childsName") String childsName) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser != null) {
			observerUser.setName(name);
			observerUser.setLastName(lastName);
			observerUser.setNumberId(numberId);
			observerUser.setPassword(password);
			observerUser.setChildsName(childsName);
			try {
				observerUserService.saveOrUpdate(observerUser);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user id");
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject json = new JSONObject();
		json.put("userId", observerUser.getUserId());
		json.put("name", observerUser.getName());
		json.put("lastName", observerUser.getLastName());
		json.put("numberId", observerUser.getNumberId());
		json.put("username", observerUser.getUsername());
		json.put("password", observerUser.getPassword());
		json.put("childsName", observerUser.getChildsName());
		data.put(json);
		
		response.put("code",  200);
		response.put("data",  data);
		Paginator paginator = new Paginator();
		response.put("paginator", paginator.PaginatorEmpty());
		response.put("status", "success");
		
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/deleteObserverUser", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody String deleteObserverUser(@RequestParam("id") int id) {

		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
			
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser !=null) {
			try {
				observerUserService.deleteObserverUser(id);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
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

	@RequestMapping(value = "/userObserveeOfObserverUserById", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeOfObserverUser(@RequestParam("id") int id) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser != null) {
			UserObservee observee = observerUser.getDriver();
			if (observee != null) {
				JSONObject json = new JSONObject();
				json.put("name", observee.getName());
				json.put("lastName", observee.getLastName());
				json.put("privacyKey", observee.getPrivacyKey());
				json.put("companyName", observee.getCompanyName());
				json.put("licensePlate", observee.getLicensePlate());
				json.put("carRegistration", observee.getCarRegistration());
				data.put(json);
			} else {
				ErrorCode errorCode = new ErrorCode(500, "Driver not exists");
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
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
	
	@RequestMapping(value = "/userObserveeOfObserverUserByUsername", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeOfObserverUserByUsername(@RequestParam("username") String username) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserByUsername(username);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser != null) {
			UserObservee observee = observerUser.getDriver();
			if (observee != null) {
				JSONObject json = new JSONObject();
				json.put("name", observee.getName());
				json.put("lastName", observee.getLastName());
				json.put("privacyKey", observee.getPrivacyKey());
				json.put("companyName", observee.getCompanyName());
				json.put("licensePlate", observee.getLicensePlate());
				json.put("carRegistration", observee.getCarRegistration());
				data.put(json);
			} else {
				ErrorCode errorCode = new ErrorCode(500, "Driver not exists");
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect username");
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
	
	@RequestMapping(value = "/userObserveeOfObserverUserByEmail", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findUserObserveeOfObserverUserByEmail(@RequestParam("email") String email) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserByEmail(email);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser != null) {
			UserObservee observee = observerUser.getDriver();
			if (observee != null) {
				JSONObject json = new JSONObject();
				json.put("name", observee.getName());
				json.put("lastName", observee.getLastName());
				json.put("privacyKey", observee.getPrivacyKey());
				json.put("companyName", observee.getCompanyName());
				json.put("licensePlate", observee.getLicensePlate());
				json.put("carRegistration", observee.getCarRegistration());
				data.put(json);
			} else {
				ErrorCode errorCode = new ErrorCode(500, "Driver not exists");
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect email");
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
	
	@RequestMapping(value = "/ObserverUserMessages", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findObserverUserMessages(@RequestParam("id") int id) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observerUser != null) {
			List<Message> messages = (List<Message>) observerUser.getObserverUserMessages();
			
			for (Message message : messages) {
				JSONObject json = new JSONObject();
				json.put("description", message.getDescription());
				json.put("userObservee", message.getUserObservee().getName() + " " + message.getUserObservee().getLastName());
				json.put("observerUser", message.getObserverUser().getName() + " " + message.getObserverUser().getLastName());
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
	
	@RequestMapping(value = "/observerUserAddress", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findObserverUserAddress(@RequestParam("id") int id) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		
		if (observerUser != null) {
			List<Address> addresses = (List<Address>) observerUser.getObserverUserAddress();
			
			for (Address address : addresses) {
				JSONObject json = new JSONObject();
				json.put("street", address.getStreet());
				json.put("number", address.getNumber());
				json.put("floor", address.getFloor());
				json.put("apartament", address.getApartament());
				json.put("zipCode", address.getZipCode());
				json.put("city", address.getCity());
				json.put("state", address.getState());
				json.put("country", address.getCountry());
				json.put("street", address.getStreet());
				json.put("latitude", address.getLatitude());
				json.put("longitud", address.getLongitude());
				data.put(json);
			}
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
	
	@RequestMapping(value = "/observerUserArrivals", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findobserverUserArrivals(@RequestParam("id") int id) {
		
		ObserverUser observerUser;
		try {
			observerUser = observerUserService.findObserverUserById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		
		if (observerUser != null) {
			List<Arrival> arrivals = (List<Arrival>) observerUser.getObserverUserArrival();
			
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