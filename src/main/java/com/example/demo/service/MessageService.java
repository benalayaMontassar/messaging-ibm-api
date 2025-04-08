package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }
}
