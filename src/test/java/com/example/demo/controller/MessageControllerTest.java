package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.mq.MqReceiver;
import com.example.demo.service.MessageService;
import com.example.demo.service.MqReceiverService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JmsTemplate jmsTemplate;

    @MockBean
    private MessageService messageService;

    @Test
    public void testPublishMessageSuccess() throws Exception {
        // Mock l'envoi du message dans la file MQ
        doNothing().when(jmsTemplate).convertAndSend(anyString(), anyString());

        // Simuler une requête POST avec un message valide
        mockMvc.perform(post("/messages/publish")  // Correct path: /messages/publish
                .param("message", "Valid message"))
                .andExpect(status().isOk())
                .andExpect(content().string("Message publié avec succès : Valid message"));

        verify(jmsTemplate, times(1)).convertAndSend("TEST.QUEUE", "Valid message");
    }

    @Test
    public void testGetMessages() throws Exception {
        // Préparer les données simulées
        Message message = new Message();
        message.setContent("Message 1");
        List<Message> messages = List.of(message);

        // Simuler le comportement du service
        when(messageService.getMessages()).thenReturn(messages);

        // Tester l'API GET /api/messages
        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Message 1"));
    }
}
