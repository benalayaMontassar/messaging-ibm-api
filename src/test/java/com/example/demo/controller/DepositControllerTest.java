package com.example.demo.controller;

import com.example.demo.model.Deposit;
import com.example.demo.model.dto.DepositDto;
import com.example.demo.service.DepositService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DepositControllerTest {
    private final static String GIFT = "gift";
    private final static String USERID = "123";
    private final MockMvc mockMvc;
    @InjectMocks
    DepositController depositController;
    @Mock
    DepositService depositService;

    public DepositControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(depositController).build();
    }

    @Test
    public void createDeposit_should_return_HTTPSTATUS_200_when_object_succefully_created() throws Exception {
        DepositDto depositToSave = new DepositDto();
        depositToSave.setType(GIFT);
        depositToSave.setAmount(100.0);
        depositToSave.setDistributionDate("2025-01-01");
        depositToSave.setUserId(USERID);
        Deposit createdDeposit = new Deposit(GIFT, 100.0, LocalDate.of(2025, 1, 1), LocalDate.of(2026, 1, 1), USERID);
        when(depositService.createDeposit(GIFT, 100.0, LocalDate.of(2025, 1, 1), USERID)).thenReturn(createdDeposit);

        mockMvc.perform(post("/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(depositToSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value(GIFT))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andReturn();
    }

    public void getUserBalance_should_return_the_correct_value() throws Exception {
        when(depositService.calculateUserBalance(USERID)).thenReturn(150.0);
        mockMvc.perform(get("/deposit/balance/" + USERID))
                .andExpect(status().isOk())
                .andExpect(content().string("150.0"));
    }
}
