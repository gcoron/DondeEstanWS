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

import com.WebService.DondeEstanApp.model.Address;
import com.WebService.DondeEstanApp.model.Arrival;
import com.WebService.DondeEstanApp.model.Message;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.ObserverUserService;
import com.WebService.DondeEstanApp.service.UserObserveeService;

@RestController
@RequestMapping(value = "/wsuor")
public class ObserverUserController {

	private static final Logger logger = LoggerFactory.getLogger(ObserverUserController.class);
	
	@Autowired
	ObserverUserService observerUserService;
	
	@Autowired
	UserObserveeService userObserveeService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/observerUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List getListUsuarioObservador() {
		List usuariosObservadores = observerUserService.getListObserverUser();

		return usuariosObservadores;
	}

	@RequestMapping(value = "/uObserver", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ObserverUser findObserverUserById(@RequestParam("id") int id, @RequestBody ObserverUser observerUser) {
		ObserverUser uObservador = observerUserService.findObserverUserById(id);

		return uObservador;
	}
	
	@RequestMapping(value = "/uObserverByUsername", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ObserverUser findObserverUserByUsername(@RequestParam("username") String username, @RequestBody ObserverUser observerUser) {
		ObserverUser uObserver = observerUserService.findObserverUserByUsername(username);

		return uObserver;
	}
	
	@RequestMapping(value = "/uObservadorByEmail", method = RequestMethod.GET, headers = "Accept=application/json")//, headers = "Accept=application/json")
	public @ResponseBody ObserverUser findUsuarioObservadorByEmail(@RequestParam("email") String email) {
		ObserverUser uObserver = observerUserService.findObserverUserByEmail(email);

		return uObserver;
	}
	
	// http://localhost:8080/DondeEstanApp/wsuor/saveObserverUser?nombre=Pablo&apellido=Coronel&dni=37283268&email=paablo.c3@gmail.com&nombreUsuario=pcoronel&password=pcoronel1234&nombreHijx=Luz&idChofer_PersonaFK=7
	@RequestMapping(value = "/saveObserverUser", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String saveObserverUser(@RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("numberId") long numberId, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("childsName") String childsName) {
		ObserverUser uObserver = new ObserverUser();
		uObserver.setName(name);
		uObserver.setLastName(lastName);
		uObserver.setNumberId(numberId);
		uObserver.setEmail(email);
		uObserver.setUsername(username);
		uObserver.setPassword(password);
		uObserver.setChildsName(childsName);
		
		JSONObject response = new JSONObject();
		try {
			observerUserService.saveOrUpdate(uObserver);
			response.put("exito", Boolean.TRUE);
		} catch (Exception e) {
			response.put("error", e.getMessage());
		}
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/saveDriverInObserverUser", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String saveDriverInObserverUser(@RequestParam("idObserverUser") int id, @RequestParam("privacyKey") String privacyKey) {
		ObserverUser uObserver = new ObserverUser();
		
		ObserverUser observer = observerUserService.findObserverUserById(id);
		UserObservee uObservee = userObserveeService.findUserObserveeByPrivacyKey(privacyKey);
		
		uObserver.setUserId(observer.getUserId());
		uObserver.setName(observer.getName());
		uObserver.setLastName(observer.getLastName());
		uObserver.setNumberId(observer.getNumberId());
		uObserver.setEmail(observer.getEmail());
		uObserver.setUsername(observer.getUsername());
		uObserver.setPassword(observer.getPassword());
		uObserver.setChildsName(observer.getChildsName());
		uObserver.setDriver(uObservee);
		
		JSONObject response = new JSONObject();
		try {
			observerUserService.saveOrUpdate(uObserver);
			response.put("success", Boolean.TRUE);
		} catch (Exception e) {
			response.put("error", e.getMessage());
		}
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody ObserverUser update(@RequestParam("id") int id, @RequestBody ObserverUser observerUser) {
		observerUser.setUserId(id);
		observerUserService.saveOrUpdate(observerUser);

		return observerUser;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody ObserverUser delete(@RequestParam("id") int id) {
		ObserverUser observerUser = observerUserService.findObserverUserById(id);
		observerUserService.deleteObserverUser(id);

		return observerUser;
	}
	
	@RequestMapping(value = "/loginObserveeUser", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String loginObserveeUser(@RequestParam("nombreUsuario") String username, @RequestParam("password") String password) {
		
		ObserverUser uObserver = observerUserService.findObserverUserByUsername(username);
		JSONObject response = new JSONObject();
		
		if (uObserver != null) {
			if (uObserver.getPassword().equals(password)) { //(EncryptionUtil.encode(password))) {
				response.put("success", Boolean.TRUE);
				response.put("username", uObserver.getUsername());
				response.put("name", uObserver.getName());
				response.put("lastName", uObserver.getLastName());
				response.put("numberId", uObserver.getNumberId());
			} else {
				response.put("success", Boolean.FALSE);
				response.put("error", "La contrase�a ingresada es incorrecta");
				response.put("nroError", 2);
			}
		} else {
			response.put("exito", Boolean.FALSE);
			response.put("error", "El nombre de usuario ingresado es incorrecto");
			response.put("nroError", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/findUserObserveeOfObserverUserById", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findUserObserveeOfObserverUser(@RequestParam("id") int id) {
		
		ObserverUser uObserver = observerUserService.findObserverUserById(id);
		JSONObject response = new JSONObject();
		if (uObserver.equals(null)) {
			response.put("success", "El usuario no ha sido encontrado");
			response.put("nroError", 1);
		}
		UserObservee uObservado = uObserver.getDriver();
		
		if (uObservado != null) {
			response.put("success", Boolean.TRUE);
			response.put("name", uObservado.getName());
			response.put("lastName", uObservado.getLastName());
			response.put("privacyKey", uObservado.getPrivacyKey());
			response.put("companyName", uObservado.getCompanyName());
			response.put("licensePlate", uObservado.getLicensePlate());
			response.put("matricula", uObservado.getCarRegistration());
		} else {
			response.put("success", "El usuario no ha sido encontrado");
			response.put("error", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/findUserObserveeOfObserverUserByUsername", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findUserObserveeOfObserverUserByUsername(@RequestParam("username") String username, @RequestBody ObserverUser observerUser) {
		
		ObserverUser uObserver = observerUserService.findObserverUserByUsername(username);
		JSONObject response = new JSONObject();
		if (uObserver.equals(null)) {
			response.put("success", "El usuario no ha sido encontrado");
			response.put("nroError", 1);
		}
		UserObservee uObservee = uObserver.getDriver();
		
		if (uObservee != null) {
			response.put("success", Boolean.TRUE);
			response.put("name", uObservee.getName());
			response.put("lastName", uObservee.getLastName());
			response.put("privacyKey", uObservee.getPrivacyKey());
			response.put("companyName", uObservee.getCompanyName());
			response.put("licensePlate", uObservee.getLicensePlate());
			response.put("matricula", uObservee.getCarRegistration());
		} else {
			response.put("exito", "El usuario no ha sido encontrado");
			response.put("error", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/findUserObserveeOfObserverUserByEmail", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findUserObserveeOfObserverUserByEmail(@RequestParam("email") String email, @RequestBody ObserverUser observerUser) {
		
		ObserverUser uObserver = observerUserService.findObserverUserByEmail(email);
		JSONObject response = new JSONObject();
		if (uObserver.equals(null)) {
			response.put("exito", "El usuario no ha sido encontrado");
			response.put("nroError", 1);
		}
		UserObservee uObservee = uObserver.getDriver();
		
		if (uObservee != null) {
			response.put("success", Boolean.TRUE);
			response.put("name", uObservee.getName());
			response.put("lastName", uObservee.getLastName());
			response.put("privacyKey", uObservee.getPrivacyKey());
			response.put("companyName", uObservee.getCompanyName());
			response.put("licensePlate", uObservee.getLicensePlate());
			response.put("matricula", uObservee.getCarRegistration());
		} else {
			response.put("success", "El usuario no ha sido encontrado");
			response.put("nroError", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/ObserverUserMessages", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findObserverUserMessages(@RequestParam("id") int observerId) {
		ObserverUser uObserver = observerUserService.findObserverUserById(observerId);
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		
		if (uObserver != null) {
			response.put("success", Boolean.TRUE);
			List<Message> messages = (List<Message>) uObserver.getObserverUserMessages();
			
			for (Message message : messages) {
				JSONObject json = new JSONObject();
				json.put("description", message.getDescription());
				json.put("name", message.getUserObservee().getName() + " " + message.getUserObservee().getLastName());
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
	
	@RequestMapping(value = "/observerUserAddress", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findObserverUserAddress(@RequestParam("id") int observerId) {
		ObserverUser uObserver = observerUserService.findObserverUserById(observerId);
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		
		if (uObserver != null) {
			response.put("exito", Boolean.TRUE);
			List<Address> addresses = (List<Address>) uObserver.getObserverUserAddress();
			
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
				
				responseArray.put(json);
			}
			response.put("adresses", responseArray);
		} else {
			response.put("success", Boolean.FALSE);
			response.put("error", "El usuario ingresado es incorrecto");
			response.put("error", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/observerUserArrivals", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findobserverUserArrivals(@RequestParam("id") int observerId) {
		ObserverUser uObserver = observerUserService.findObserverUserById(observerId);
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		
		if (uObserver != null) {
			response.put("success", Boolean.TRUE);
			List<Arrival> arrivals = (List<Arrival>) uObserver.getObserverUserArrival();
			
			for (Arrival arrival : arrivals) {
				JSONObject json = new JSONObject();
				json.put("dayHour", arrival.getDayHour());
				json.put("userObserveeName", arrival.getUserObservee().getName() + " " + arrival.getUserObservee().getLastName());
				json.put("observerUserName", arrival.getObserverUser().getName() + " " + arrival.getObserverUser().getLastName());
				json.put("observerUserAddress", arrival.getAddress().getStreet() + " " + arrival.getAddress().getNumber() + ", Latitude: " + arrival.getAddress().getLatitude() + ", Longitude: " + arrival.getAddress().getLongitude());
				json.put("userObserveeLocation", arrival.getLocation().getLatitude() + ", " + arrival.getLocation().getLongitude());
				responseArray.put(json);
			}
			response.put("arrivals", responseArray);
		} else {
			response.put("exito", Boolean.FALSE);
			response.put("error", "El usuario ingresado es incorrecto");
			response.put("nroError", 1);
		}
		
		logger.debug(response.toString());
		return response.toString();
	}
	
}
