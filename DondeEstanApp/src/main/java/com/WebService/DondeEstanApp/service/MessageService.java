package com.WebService.DondeEstanApp.service;

import java.util.List;

import com.WebService.DondeEstanApp.model.Message;

public interface MessageService {

	@SuppressWarnings("rawtypes")
	public List getListMessage();

	public void saveOrUpdate(Message message);

	public void deleteMessage(int id);

	public Message findMessageById(int id);

}