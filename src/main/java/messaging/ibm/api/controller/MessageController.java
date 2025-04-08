package messaging.ibm.api.controller;

import messaging.ibm.api.model.Message;
import messaging.ibm.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.jms.core.JmsTemplate;
import java.util.List;

@RestController
@RequestMapping("/messages")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getMessages();
    }
    @PostMapping("/publish")
    public String publishMessage(@RequestParam String message) {
        String queueName = "TEST.QUEUE";
        jmsTemplate.convertAndSend(queueName, message);
        return "Message publié avec succès : " + message;
    }
}
