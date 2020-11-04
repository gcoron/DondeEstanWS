package com.WebService.DondeEstanApp.controller;

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

import com.WebService.DondeEstanApp.model.Message;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.MessageService;
import com.WebService.DondeEstanApp.service.UserObserveeService;
import com.WebService.DondeEstanApp.service.ObserverUserService;

@RestController
@RequestMapping(value = "/wsm")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	MessageService messageService;

	@Autowired
	UserObserveeService userObserveeService;
	
	@Autowired
	ObserverUserService observerUserService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/messages", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List getListMessage() {
		List messages = messageService.getListMessage();

		return messages;
	}

	@RequestMapping(value = "/addMessage", method = RequestMethod.POST)
	public @ResponseBody Message addMessage(@RequestBody Message message) {
		messageService.saveOrUpdate(message);

		return message;
	}

	@RequestMapping(value = "/saveMessage", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String saveMessage(@RequestParam("descripcion") String description, @RequestParam("userObserveeId") int userObserveeId, @RequestParam("observerUserId") int observerUserId) {
		UserObservee userObservee = userObserveeService.findUserObserveeById(userObserveeId);
		ObserverUser observerUser = observerUserService.findObserverUserById(observerUserId);
		JSONObject response = new JSONObject();
		
		if (userObservee != null) {
			Message message = new Message();
			message.setDescription(description);
			message.setUserObservee(userObservee);
			message.setObserverUser(observerUser);
			
			try {
				messageService.saveOrUpdate(message);
				response.put("success", Boolean.TRUE);
			} catch (Exception e) {
				response.put("error", e.getMessage());
			}
		} else {
			response.put("error", "Usuario Observado no encontrado.");
		}		
		
		logger.debug(response.toString());
		return response.toString();
	}
	
	@RequestMapping(value = "/updateMessage", method = RequestMethod.PUT)
	public @ResponseBody String updateMessage(@RequestParam("id") int id, @RequestParam("description") String description, @RequestParam("userObserveeId") int userObserveeId, @RequestParam("observerUserId") int observerUserId) {
		UserObservee userObservee = userObserveeService.findUserObserveeById(userObserveeId);
		ObserverUser observerUser = observerUserService.findObserverUserById(observerUserId);
		JSONObject response = new JSONObject();
		
		if (userObservee != null) {
			Message message = new Message();
			message.setMessageId(id);
			message.setDescription(description);
			message.setUserObservee(userObservee);
			message.setObserverUser(observerUser);
			
			try {
				messageService.saveOrUpdate(message);
				response.put("success", Boolean.TRUE);
			} catch (Exception e) {
				response.put("error", e.getMessage());
			}
		} else {
			response.put("error", "Usuario Observado no encontrado.");
		}		
		
		logger.debug(response.toString());
		return response.toString();
	}

	@RequestMapping(value = "/deleteMessage", method = RequestMethod.DELETE)
	public @ResponseBody Message deleteMessage(@RequestParam("id") int id) {
		Message message = messageService.findMessageById(id);
		messageService.deleteMessage(id);

		return message;
	}
}
