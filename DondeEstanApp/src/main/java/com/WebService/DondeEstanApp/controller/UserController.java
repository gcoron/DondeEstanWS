package com.WebService.DondeEstanApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.WebService.DondeEstanApp.model.User;
import com.WebService.DondeEstanApp.service.UserService;

@RestController
@RequestMapping(value = "/wsu")
public class UserController {

	@Autowired
	UserService userService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/persons", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List getListPersona() {
		List persons = userService.getListUser();

		return persons;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody User add(@RequestBody User user) {
		userService.saveOrUpdate(user);

		return user;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody User update(@RequestParam("id") int id, @RequestBody User user) {
		user.setUserId(id);
		userService.saveOrUpdate(user);

		return user;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody User delete(@RequestParam("id") int id) {
		User user = userService.findUserById(id);
		userService.deleteUser(id);

		return user;
	}

}