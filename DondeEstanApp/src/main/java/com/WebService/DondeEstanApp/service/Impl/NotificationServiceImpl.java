package com.WebService.DondeEstanApp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebService.DondeEstanApp.dao.NotificationDAO;
import com.WebService.DondeEstanApp.model.Notification;
import com.WebService.DondeEstanApp.service.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	private NotificationDAO notificationDAO;

	@Autowired
	public void setNotificationDao(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListNotification() {
		return notificationDAO.getListNotification();
	}

	@Override
	public void saveOrUpdate(Notification notification) {
		notificationDAO.saveOrUpdate(notification);
	}

	@Override
	public void deleteNotification(int id) {
		notificationDAO.deleteNotification(id);
	}

	@Override
	public Notification findNotificationById(int id) {
		return notificationDAO.findNotificationById(id);
	}

}
