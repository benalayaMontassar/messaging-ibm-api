package com.example.demo.mq;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jms.TextMessage;
import java.time.LocalDateTime;

@Service
public class MqReceiver {

    private final MessageRepository messageRepository;

    @Autowired
    public MqReceiver(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void receiveMessage(javax.jms.Message jmsMessage) {
        try {
            if (jmsMessage instanceof TextMessage) {
                String messageContent = ((TextMessage) jmsMessage).getText();
                storeMessageInDatabase(messageContent, "TEXT_MESSAGE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void storeMessageInDatabase(String content, String messageType) {
        // Create a new Message entity
        Message message = new Message();
        message.setContent(content);
        message.setReceivedAt(LocalDateTime.now());
        message.setMessageType(messageType);
        // Save the message to the database
        messageRepository.save(message);
    }
}
