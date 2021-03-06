package com.WebService.DondeEstanApp.service;

import java.util.List;

import com.WebService.DondeEstanApp.model.Notification;

public interface NotificationService {

	@SuppressWarnings("rawtypes")
	public List getListNotification();

	public void saveOrUpdate(Notification notification);

	public void deleteNotification(int id);

	public Notification findNotificationById(int id);

}
