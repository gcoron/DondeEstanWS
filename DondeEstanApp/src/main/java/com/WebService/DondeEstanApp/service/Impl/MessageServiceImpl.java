package com.WebService.DondeEstanApp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebService.DondeEstanApp.dao.MessageDAO;
import com.WebService.DondeEstanApp.model.Message;
import com.WebService.DondeEstanApp.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	private MessageDAO messageDAO;

	@Autowired
	public void setMessageDao(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListMessage() {
		return messageDAO.getListMessage();
	}

	@Override
	public void saveOrUpdate(Message message) {
		messageDAO.saveOrUpdate(message);
	}

	@Override
	public void deleteMessage(int id) {
		messageDAO.deleteMessage(id);
	}

	@Override
	public Message findMessageById(int id) {
		return messageDAO.findMessageById(id);
	}

}
