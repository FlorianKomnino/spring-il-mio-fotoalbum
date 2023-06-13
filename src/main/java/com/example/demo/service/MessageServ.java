package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Message;
import com.example.demo.repo.MessageRepo;

@Service
public class MessageServ {
	@Autowired
	private MessageRepo messageRepo;
	
	public List<Message> findAll() {
		return messageRepo.findAll();
	}
	
	public Message save(Message message) {
		return messageRepo.save(message);
	}
}
