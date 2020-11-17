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

import com.WebService.DondeEstanApp.model.Message;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.model.ObserverUser;
import com.WebService.DondeEstanApp.service.MessageService;
import com.WebService.DondeEstanApp.service.UserObserveeService;
import com.WebService.DondeEstanApp.utils.ErrorCode;
import com.WebService.DondeEstanApp.utils.Paginator;
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
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/messages", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getListMessage() {
		
		List<Message> messages;
		try {
			messages = messageService.getListMessage();
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		for (Message message : messages) {
			JSONObject json = new JSONObject();
			json.put("messageId", message.getMessageId());
			json.put("description", message.getDescription());
			JSONObject jsonUserObservee = new JSONObject();
			jsonUserObservee.put("userId", message.getUserObservee().getUserId());
			jsonUserObservee.put("name", message.getUserObservee().getName());
			jsonUserObservee.put("lastName", message.getUserObservee().getLastName());
			jsonUserObservee.put("email", message.getUserObservee().getEmail());
			jsonUserObservee.put("companyName", message.getUserObservee().getCompanyName());
			jsonUserObservee.put("licensePlate", message.getUserObservee().getLicensePlate());
			jsonUserObservee.put("carRegistration", message.getUserObservee().getCarRegistration());
			json.put("userObservee", jsonUserObservee);
			JSONObject jsonObserverUser = new JSONObject();
			jsonObserverUser.put("userId", message.getObserverUser().getUserId());
			jsonObserverUser.put("name", message.getObserverUser().getName());
			jsonObserverUser.put("lastName", message.getObserverUser().getLastName());
			jsonObserverUser.put("email", message.getObserverUser().getEmail());
			json.put("observerUser", jsonObserverUser);
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

	@RequestMapping(value = "/saveMessage", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String saveMessage(@RequestParam("description") String description, @RequestParam("userObserveeId") int userObserveeId, @RequestParam("observerUserId") int observerUserId) {
		
		UserObservee userObservee;
		ObserverUser observerUser;
		try {
			userObservee = userObserveeService.findUserObserveeById(userObserveeId);
			observerUser = observerUserService.findObserverUserById(observerUserId);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		
		if (userObservee != null) {
			if (observerUser != null) {
				Message message = new Message();
				message.setDescription(description);
				message.setUserObservee(userObservee);
				message.setObserverUser(observerUser);
				try {
					messageService.saveOrUpdate(message);
				} catch (Exception e) {
					ErrorCode errorCode = new ErrorCode(500, e.getMessage());
					logger.debug(errorCode.toString());
					return errorCode.toString();
				}
				JSONObject json = new JSONObject();
				json.put("messageId", message.getMessageId());
				json.put("description", message.getDescription());
				JSONObject jsonUserObservee = new JSONObject();
				jsonUserObservee.put("userId", message.getUserObservee().getUserId());
				jsonUserObservee.put("name", message.getUserObservee().getName());
				jsonUserObservee.put("lastName", message.getUserObservee().getLastName());
				jsonUserObservee.put("email", message.getUserObservee().getEmail());
				jsonUserObservee.put("companyName", message.getUserObservee().getCompanyName());
				jsonUserObservee.put("licensePlate", message.getUserObservee().getLicensePlate());
				jsonUserObservee.put("carRegistration", message.getUserObservee().getCarRegistration());
				json.put("userObservee", jsonUserObservee);
				JSONObject jsonObserverUser = new JSONObject();
				jsonObserverUser.put("userId", message.getObserverUser().getUserId());
				jsonObserverUser.put("name", message.getObserverUser().getName());
				jsonObserverUser.put("lastName", message.getObserverUser().getLastName());
				jsonObserverUser.put("email", message.getObserverUser().getEmail());
				json.put("observerUser", jsonObserverUser);
				data.put(json);
			} else {
				ErrorCode errorCode = new ErrorCode(500, "Incorrect observer user id");
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect user observee id");
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
	
	@RequestMapping(value = "/updateMessage", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String updateMessage(@RequestParam("id") int id, @RequestParam("description") String description) {
		
		Message message = messageService.findMessageById(id);
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (message != null) {
			message.setMessageId(id);
			message.setDescription(description);
			try {
				messageService.saveOrUpdate(message);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			JSONObject json = new JSONObject();
			json.put("messageId", message.getMessageId());
			json.put("description", message.getDescription());
			JSONObject jsonUserObservee = new JSONObject();
			jsonUserObservee.put("userId", message.getUserObservee().getUserId());
			jsonUserObservee.put("name", message.getUserObservee().getName());
			jsonUserObservee.put("lastName", message.getUserObservee().getLastName());
			jsonUserObservee.put("email", message.getUserObservee().getEmail());
			jsonUserObservee.put("companyName", message.getUserObservee().getCompanyName());
			jsonUserObservee.put("licensePlate", message.getUserObservee().getLicensePlate());
			jsonUserObservee.put("carRegistration", message.getUserObservee().getCarRegistration());
			json.put("userObservee", jsonUserObservee);
			JSONObject jsonObserverUser = new JSONObject();
			jsonObserverUser.put("userId", message.getObserverUser().getUserId());
			jsonObserverUser.put("name", message.getObserverUser().getName());
			jsonObserverUser.put("lastName", message.getObserverUser().getLastName());
			jsonObserverUser.put("email", message.getObserverUser().getEmail());
			json.put("observerUser", jsonObserverUser);
			data.put(json);
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect message id");
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

	@RequestMapping(value = "/deleteMessage", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody String deleteMessage(@RequestParam("id") int id) {
		
		Message message;
		try {
			message = messageService.findMessageById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (message != null) {
			try {
				messageService.deleteMessage(id);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect message id");
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
