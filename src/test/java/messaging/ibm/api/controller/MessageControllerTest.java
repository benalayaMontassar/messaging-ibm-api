package messaging.ibm.api.controller;

import messaging.ibm.api.model.Message;
import messaging.ibm.api.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
        doNothing().when(jmsTemplate).convertAndSend(anyString(), anyString());
        mockMvc.perform(post("/messages/publish")
                .param("message", "Valid message"))
                .andExpect(status().isOk())
                .andExpect(content().string("Message publié avec succès : Valid message"));
        verify(jmsTemplate, times(1)).convertAndSend("TEST.QUEUE", "Valid message");
    }

    @Test
    public void testGetMessages() throws Exception {
        Message message = new Message();
        message.setContent("Message 1");
        List<Message> messages = List.of(message);
        when(messageService.getMessages()).thenReturn(messages);
        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Message 1"));
    }
}
