package com.WebService.DondeEstanApp.controller;

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

import com.WebService.DondeEstanApp.model.Arrival;
import com.WebService.DondeEstanApp.model.Location;
import com.WebService.DondeEstanApp.model.Message;
import com.WebService.DondeEstanApp.model.Notification;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.UserObserveeService;

@RestController
@RequestMapping(value = "/wsuo")
public class UserObserveeController {

	private static final Logger logger = LoggerFactory.getLogger(UserObserveeController.class);
	
	@Autowired
	UserObserveeService userObserveeService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getUsersObservees", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List getListUserObservee() {
		List usersObservees = userObserveeService.getListUserObservee();

		return usersObservees;
	}

	@RequestMapping(value = "/uObserveeById", method = RequestMethod.GET, headers = "Accept=application/json")//produces = "text/plain;charset=UTF-8")//
	public @ResponseBody UserObservee findUserObserveeById(@RequestParam("id") int id) {
		UserObservee uObservee = userObserveeService.findUserObserveeById(id);

		return uObservee;
	}
	
	@RequestMapping(value = "/onlyuObserveeById", method = RequestMethod.GET, headers = "Accept=application/json")//produces = "text/plain;charset=UTF-8")//
	public @ResponseBody JSONObject findOnlyUserObserveeById(@RequestParam("id") int id) {
		JSONObject uObservee = userObserveeService.findOnlyUserObserveeById(id);

		return uObservee;
	}
	
	// http://localhost:8080/DondeEstanApp/wsuo/saveUserObservee?nombre=prueba&apellido=prueba&dni=12345678&email=prueba@prueba&nombreUsuario=prueba&password=prueba1234&nombreEmpresa=empresa prueba&patente=abc123&matricula=123
	@RequestMapping(value = "/saveUserObservee", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String saveObservedUser(@RequestParam("nombre") String name, @RequestParam("lastName") String lastName, @RequestParam("numberId") long numberId, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("companyName") String companyName, @RequestParam("licensePlate") String licensePlate, @RequestParam("matricula") String matricula) {
		UserObservee uObservee = new UserObservee();
		uObservee.setName(name);
		uObservee.setLastName(lastName);
		uObservee.setNumberId(numberId);
		uObservee.setEmail(email);
		uObservee.setPrivacyKey(name + "." + lastName + "." + numberId + "." + username);
		uObservee.setUsername(username);
		uObservee.setPassword(password);
		uObservee.setCompanyName(companyName);
		uObservee.setLicensePlate(licensePlate);
		uObservee.setCarRegistration(matricula);
		
		UserObservee userByUsername = findUserObserveeByUsername(username);
		UserObservee userByEmail = findUserObserveeByEmail(email);
		JSONObject response = new JSONObject();
		
		if (userByUsername != null) {
			response.put("error", "El usuario ya existe.");
			} else if (userByEmail != null) {
				response.put("error", "El email ya existe.");
			} else {
				try {
					userObserveeService.saveOrUpdate(uObservee);
					response.put("success", Boolean.TRUE);
					response.put("userId", uObservee.getUserId());
					response.put("name", uObservee.getName());
					response.put("lastName", uObservee.getLastName());
					response.put("numberId", uObservee.getNumberId());
					response.put("companyName", uObservee.getCompanyName());
					response.put("licensePlate", uObservee.getLicensePlate());
					response.put("carRegistration", uObservee.getCarRegistration());
					response.put("privacyKey", name + "." + lastName + "." + numberId);
				} catch (Exception e) {
					response.put("error", e.getMessage());
				}
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/addUserObservee", method = RequestMethod.POST)
	public @ResponseBody String add(@RequestBody UserObservee userObservee) {

		JSONObject response = new JSONObject();
		try {
			userObserveeService.saveOrUpdate(userObservee);
			response.put("success", Boolean.TRUE);
		} catch (Exception e) {
			response.put("error", e.getMessage());
		}
		
		return response.toString();
	}
	
	@RequestMapping(value = "/uObserveeByUsername", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody UserObservee findUserObserveeByUsername(@RequestParam("username") String username) {
		UserObservee uObservee = userObserveeService.findUserObserveeByUsername(username);

		return uObservee;
	}
	
	@RequestMapping(value = "/uObserveeByPrivacyKey", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody UserObservee findUserObserveeByClavePrivacidad(@RequestParam("clavePrivacidad") String privacyKey) {
		UserObservee uObservee = userObserveeService.findUserObserveeByPrivacyKey(privacyKey);

		return uObservee;
	}

	@RequestMapping(value = "/uObserveeByEmail", method = RequestMethod.GET, headers = "Accept=application/json")//, headers = "Accept=application/json")
	public @ResponseBody UserObservee findUserObserveeByEmail(@RequestParam("email") String email) {
		UserObservee uObservee = userObserveeService.findUserObserveeByEmail(email);

		return uObservee;
	}
	
	@RequestMapping(value = "/listObserversUsersOfUserObservee", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findUsersObserversByIdUserObservee(@RequestParam("id") int id) {
		UserObservee uObservee = userObserveeService.findUserObserveeById(id);
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		
		if (uObservee != null) {
			response.put("exito", Boolean.TRUE);
			List<ObserverUser> observers = (List<ObserverUser>) uObservee.getObservadores();
			
			for (ObserverUser observer : observers) {
				JSONObject json = new JSONObject();
				json.put("id", observer.getUserId());
				json.put("name", observer.getName());
				json.put("lastName", observer.getLastName());
				json.put("childsName", observer.getChildsName());
				responseArray.put(json);
			}
			response.put("observers", responseArray);
		} else {
			response.put("success", Boolean.FALSE);
			response.put("error", "El nombre de usuario ingresado es incorrecto");
			response.put("nroError", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/updateUserObservee", method = RequestMethod.PUT, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String update(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("numberId") long numberId, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("companyName") String companyName, @RequestParam("licensePlate") String licensePlate, @RequestParam("carRegistration") String carRegistration) {
		UserObservee userObservee = findUserObserveeById(id);
		
		UserObservee uObservee = new UserObservee();
		uObservee.setUserId(id);
		uObservee.setName(name);
		uObservee.setLastName(lastName);
		uObservee.setNumberId(numberId);
		uObservee.setEmail(email);
		uObservee.setPrivacyKey(name + "." + lastName + "." + numberId + "." + username);
		uObservee.setUsername(username);
		uObservee.setPassword(password);
		uObservee.setCompanyName(companyName);
		uObservee.setLicensePlate(licensePlate);
		uObservee.setCarRegistration(carRegistration);
		
		JSONObject response = new JSONObject();
		
		if (userObservee.equals(null)) {
			response.put("error", "El usuario no existe.");
		} else {
			try {
				userObserveeService.saveOrUpdate(uObservee);
				response.put("success", Boolean.TRUE);
				response.put("personId", uObservee.getUserId());
				response.put("name", uObservee.getName());
				response.put("lastName", uObservee.getLastName());
				response.put("numberId", uObservee.getNumberId());
				response.put("companyName", uObservee.getCompanyName());
				response.put("licensePlate", uObservee.getLicensePlate());
				response.put("carRegistration", uObservee.getCarRegistration());
				response.put("privacyKey", name + "." + lastName + "." + numberId);
			} catch (Exception e) {
				response.put("error", e.getMessage());
			}
		}
		
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/deleteUserObservee", method = RequestMethod.DELETE)
	public @ResponseBody UserObservee delete(@RequestParam("id") int id) {
		UserObservee usuarioObservado = userObserveeService.findUserObserveeById(id);
		userObserveeService.deleteUserObservee(id);

		return usuarioObservado;
	}
	
	// http://localhost:8080/DondeEstanApp/wsuo/loginObservedUser?nombreUsuario=gcoronel&password=gcoronel1234
	@RequestMapping(value = "/userObserveeLogin", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String userObserveeLogin(@RequestParam("nombreUsuario") String nombreUsuario, @RequestParam("password") String password) {
		
		UserObservee uObservee = userObserveeService.findUserObserveeByUsername(nombreUsuario);
		JSONObject response = new JSONObject();
		
		if (uObservee != null) {
			if (uObservee.getPassword().equals(password)) { //(EncryptionUtil.encode(password))) {
				response.put("success", Boolean.TRUE);
				response.put("username", uObservee.getUsername());
				response.put("name", uObservee.getName());
				response.put("lastName", uObservee.getLastName());
				response.put("numberId", uObservee.getNumberId());
			} else {
				response.put("success", Boolean.FALSE);
				response.put("error", "La contrase�a ingresada es incorrecta");
				response.put("nroError", 2);
			}
		} else {
			response.put("success", Boolean.FALSE);
			response.put("error", "El nombre de usuario ingresado es incorrecto");
			response.put("nroError", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeNotifications", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findUserObserveeNotifications(@RequestParam("id") int observeeId) {
		UserObservee uObservee = userObserveeService.findUserObserveeById(observeeId);
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		
		if (uObservee != null) {
			response.put("success", Boolean.TRUE);
			List<Notification> notifications = (List<Notification>) uObservee.getNotifications();
			
			for (Notification notification : notifications) {
				JSONObject json = new JSONObject();
				json.put("id", notification.getTitle());
				json.put("description", notification.getDescription());
				responseArray.put(json);
			}
			response.put("notifications", responseArray);
		} else {
			response.put("success", Boolean.FALSE);
			response.put("error", "El usuario ingresado es incorrecto");
			response.put("nroError", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeMessages", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findUserObserveeMessages(@RequestParam("id") int observeeId) {
		UserObservee uObservee = userObserveeService.findUserObserveeById(observeeId);
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		
		if (uObservee != null) {
			response.put("exito", Boolean.TRUE);
			List<Message> messages = (List<Message>) uObservee.getUserObserveeMessages();
			
			for (Message message : messages) {
				JSONObject json = new JSONObject();
				json.put("description", message.getDescription());
				json.put("name", message.getObserverUser().getName() + " " + message.getObserverUser().getLastName());
				responseArray.put(json);
			}
			response.put("messages", responseArray);
		} else {
			response.put("success", Boolean.FALSE);
			response.put("error", "El usuario ingresado es incorrecto");
			response.put("nroError", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeLocations", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findUserObserveeLocations(@RequestParam("id") int idObservado) {
		UserObservee uObservee = userObserveeService.findUserObserveeById(idObservado);
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		
		if (uObservee != null) {
			response.put("success", Boolean.TRUE);
			List<Location> locations = (List<Location>) uObservee.getUserObserveeLocation();
			
			for (Location location : locations) {
				JSONObject json = new JSONObject();
				json.put("latitude", location.getLatitude());
				json.put("longitude", location.getLongitude());
				json.put("dayHour", location.getDayHour());
				responseArray.put(json);
			}
			response.put("locations", responseArray);
		} else {
			response.put("success", Boolean.FALSE);
			response.put("error", "El usuario ingresado es incorrecto");
			response.put("error", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/userObserveeArrivals", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findUserObserveeArrivals(@RequestParam("id") int idObservado) {
		UserObservee uObservee = userObserveeService.findUserObserveeById(idObservado);
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		
		if (uObservee != null) {
			response.put("success", Boolean.TRUE);
			List<Arrival> arrivals = (List<Arrival>) uObservee.getUserObserveeArrival();
			
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
		} else {
			response.put("success", Boolean.FALSE);
			response.put("error", "El usuario ingresado es incorrecto");
			response.put("error", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
}