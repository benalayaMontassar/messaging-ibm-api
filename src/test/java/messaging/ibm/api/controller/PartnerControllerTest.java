package messaging.ibm.api.controller;

import messaging.ibm.api.model.Partner;
import messaging.ibm.api.service.PartnerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PartnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartnerService partnerService;

    @Test
    public void testGetPartners() throws Exception {
        Partner partner = new Partner();
        Mockito.when(partnerService.getAllPartners()).thenReturn(Arrays.asList(partner));
        mockMvc.perform(get("/partners")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    public void testAddPartner() throws Exception {
        Partner partner = new Partner();
        String json = "{\"name\": \"Test Partner\"}";
        mockMvc.perform(post("/partners").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
    }

    @Test
    public void testDeletePartner() throws Exception {
        Long id = 1L;
        Mockito.doNothing().when(partnerService).deletePartner(id);
        mockMvc.perform(delete("/partners/{id}", id)).andExpect(status().isNoContent());
    }
}
