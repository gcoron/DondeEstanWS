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

import com.WebService.DondeEstanApp.model.Notification;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.service.NotificationService;
import com.WebService.DondeEstanApp.service.UserObserveeService;
import com.WebService.DondeEstanApp.utils.ErrorCode;
import com.WebService.DondeEstanApp.utils.Paginator;

@RestController
@RequestMapping(value = "/wsn")
public class NotificationController {

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	NotificationService notificationService;

	@Autowired
	UserObserveeService userObserveeService;
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/notifications", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getListNotification() {
		
		List<Notification> notifications;
		try {
			notifications = notificationService.getListNotification();
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		for (Notification notification : notifications) {
			JSONObject json = new JSONObject();
			json.put("notificationId", notification.getNotificationId());
			json.put("title", notification.getTitle());
			json.put("description", notification.getDescription());
			json.put("userObservee", notification.getUserObservee());
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

	@RequestMapping(value = "/saveNotification", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String saveNotification(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("userObserveeId") int userObserveeId) {
		
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
			Notification notification = new Notification();
			notification.setTitle(title);
			notification.setDescription(description);
			notification.setUserObservee(userObservee);
			try {
				notificationService.saveOrUpdate(notification);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			JSONObject json = new JSONObject();
			json.put("notificationId", notification.getNotificationId());
			json.put("title", notification.getTitle());
			json.put("description", notification.getDescription());
			JSONObject jsonUserObservee = new JSONObject();
			jsonUserObservee.put("userId", notification.getUserObservee().getUserId());
			jsonUserObservee.put("name", notification.getUserObservee().getName());
			jsonUserObservee.put("lastName", notification.getUserObservee().getLastName());
			jsonUserObservee.put("email", notification.getUserObservee().getEmail());
			jsonUserObservee.put("companyName", notification.getUserObservee().getCompanyName());
			jsonUserObservee.put("licensePlate", notification.getUserObservee().getLicensePlate());
			jsonUserObservee.put("carRegistration", notification.getUserObservee().getCarRegistration());
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
	
	@RequestMapping(value = "/updateNotification", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String updateNotification(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("description") String description) {
		
		Notification notification;
		try {
			notification = notificationService.findNotificationById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (notification != null) {
			notification.setNotificationId(id);
			notification.setTitle(title);
			notification.setDescription(description);
			try {
				notificationService.saveOrUpdate(notification);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
			JSONObject json = new JSONObject();
			json.put("notificationId", notification.getNotificationId());
			json.put("title", notification.getTitle());
			json.put("description", notification.getDescription());
			JSONObject jsonUserObservee = new JSONObject();
			jsonUserObservee.put("userId", notification.getUserObservee().getUserId());
			jsonUserObservee.put("name", notification.getUserObservee().getName());
			jsonUserObservee.put("lastName", notification.getUserObservee().getLastName());
			jsonUserObservee.put("email", notification.getUserObservee().getEmail());
			jsonUserObservee.put("companyName", notification.getUserObservee().getCompanyName());
			jsonUserObservee.put("licensePlate", notification.getUserObservee().getLicensePlate());
			jsonUserObservee.put("carRegistration", notification.getUserObservee().getCarRegistration());
			json.put("userObservee", jsonUserObservee);
			data.put(json);
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect notification id");
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

	@RequestMapping(value = "/deleteNotification", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody String delete(@RequestParam("id") int id) {
		
		Notification notification;
		try {
			notification = notificationService.findNotificationById(id);
		} catch (Exception e) {
			ErrorCode errorCode = new ErrorCode(500, e.getMessage());
			logger.debug(errorCode.toString());
			return errorCode.toString();
		}
		
		JSONObject response = new JSONObject();
		JSONArray data = new JSONArray();
		if (notification != null) {
			try {
				notificationService.deleteNotification(id);
			} catch (Exception e) {
				ErrorCode errorCode = new ErrorCode(500, e.getMessage());
				logger.debug(errorCode.toString());
				return errorCode.toString();
			}
		} else {
			ErrorCode errorCode = new ErrorCode(500, "Incorrect notification id");
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
