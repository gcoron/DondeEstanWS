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

import com.WebService.DondeEstanApp.model.Notification;
import com.WebService.DondeEstanApp.model.UserObservee;
import com.WebService.DondeEstanApp.service.NotificationService;
import com.WebService.DondeEstanApp.service.UserObserveeService;

@RestController
@RequestMapping(value = "/wsn")
public class NotificationController {

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	NotificationService notificationService;

	@Autowired
	UserObserveeService userObserveeService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/notifications", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List getListNotification() {
		List notifications = notificationService.getListNotification();

		return notifications;
	}

	@RequestMapping(value = "/addNotification", method = RequestMethod.POST)
	public @ResponseBody Notification addNotifications(@RequestBody Notification notification) {
		notificationService.saveOrUpdate(notification);

		return notification;
	}

	@RequestMapping(value = "/saveNotification", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String saveObserverUser(@RequestParam("titulo") String title, @RequestParam("descripcion") String description, @RequestParam("userObserveeId") int userObserveeId) {
		UserObservee userObservee = userObserveeService.findUserObserveeById(userObserveeId);
		JSONObject response = new JSONObject();
		
		if (userObservee != null) {
			Notification notification = new Notification();
			notification.setTitle(title);
			notification.setDescription(description);
			notification.setUserObservee(userObservee);
			
			try {
				notificationService.saveOrUpdate(notification);
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
	
	@RequestMapping(value = "/updateNotification", method = RequestMethod.PUT)
	public @ResponseBody Notification update(@RequestParam("id") int id, @RequestBody Notification notification) {
		notification.setIdNotification(id);
		notificationService.saveOrUpdate(notification);

		return notification;
	}

	@RequestMapping(value = "/deleteNotification", method = RequestMethod.DELETE)
	public @ResponseBody Notification delete(@RequestParam("id") int id) {
		Notification notiication = notificationService.findNotificationById(id);
		notificationService.deleteNotification(id);

		return notiication;
	}
}
