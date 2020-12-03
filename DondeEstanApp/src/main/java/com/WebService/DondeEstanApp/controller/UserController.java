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

import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.model.User;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.service.ObserverUserService;
import com.WebService.DondeEstanApp.service.UserObserveeService;
import com.WebService.DondeEstanApp.service.UserService;
import com.WebService.DondeEstanApp.utils.ErrorCode;
import com.WebService.DondeEstanApp.utils.Paginator;

@RestController
@RequestMapping(value = "/wsu")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserObserveeService userObserveeService;
	
	@Autowired
	ObserverUserService observerUserService;

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/users", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getListUsers() {
		
		List<User> users;
		try {
			users = userService.getListUser();
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		for (User user : users) {
			JSONObject json = new JSONObject();
			json.put("userId", user.getUserId());
			json.put("name", user.getName());
			json.put("lastName", user.getLastName());
			json.put("numberId", user.getNumberId());
			json.put("email", user.getEmail());
			json.put("username", user.getUsername());
			json.put("password", user.getPassword());
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
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		UserObservee observee;
		ObserverUser observer;
		try {
			observee = userObserveeService.findUserObserveeByUsername(username);
			observer = observerUserService.findObserverUserByUsername(username);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (observee != null) {
			if (observee.getPassword().equals(password)) {
				JSONObject json = new JSONObject();
				json.put("user", "observee");
				json.put("username", observee.getUsername());
				json.put("name", observee.getName());
				json.put("lastName", observee.getLastName());
				json.put("numberId", observee.getNumberId());
				data.put(json);
			} else {
				ErrorCode errorCode = new ErrorCode(500, "Incorrect password");
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else if (observer != null) {
			if (observer.getPassword().equals(password)) {
				JSONObject json = new JSONObject();
				json.put("user", "observer");
				json.put("username", observer.getUsername());
				json.put("name", observer.getName());
				json.put("lastName", observer.getLastName());
				json.put("numberId", observer.getNumberId());
				data.put(json);
			} else {
				ErrorCode errorCode = new ErrorCode(500, "Incorrect password");
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

}
