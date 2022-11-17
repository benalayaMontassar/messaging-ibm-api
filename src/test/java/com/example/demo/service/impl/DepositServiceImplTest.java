package com.example.demo.service.impl;

import com.example.demo.model.Deposit;
import com.example.demo.repository.DepositRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepositServiceImplTest {
    private final static String GIFT = "gift";
    private final static String MEAL = "gift";
    private final static String USERID = "123";

    @InjectMocks
    private DepositServiceImpl depositService;
    @Mock
    private DepositRepository depositRepository;

    @Test
    public void createDeposit_should_create_and_save_the_correct_deposit() {
        LocalDate distributionDate = LocalDate.of(2025, 1, 1);
        Deposit expectedDeposit = new Deposit(GIFT, 100.0, distributionDate, distributionDate.plusDays(365), USERID);
        when(depositRepository.save(any())).thenReturn(expectedDeposit);
        Deposit createdDeposit = depositService.createDeposit(GIFT, 100.0, distributionDate, USERID);
        Assertions.assertNotNull(createdDeposit);
        assertEquals(expectedDeposit.getType(), createdDeposit.getType());
        assertEquals(expectedDeposit.getAmount(), createdDeposit.getAmount());
        assertEquals(expectedDeposit.getExpirationDate(), createdDeposit.getExpirationDate());
    }

    @Test
    public void createDeposit_should_handle_exception_when_type_not_valid() {
        LocalDate distributionDate = LocalDate.of(2025, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            depositService.createDeposit("invalidType", 100.0, distributionDate, "123");
        });

    }

    @Test
    public void calculateUserBalance_should_return_the_sum_of_values() {
        LocalDate today = LocalDate.now();
        List<Deposit> depositList = List.of(
                new Deposit(GIFT, 50.0, today.minusDays(10), today.plusDays(100), USERID),
                new Deposit(GIFT, 100, today.minusDays(200), today.plusDays(50), USERID),
                new Deposit(MEAL, 30.0, today.minusDays(400), today.plusDays(10), USERID),
                new Deposit(GIFT, 50.0, today.minusDays(10), today.plusDays(100), "456"));
        when(depositRepository.findByUserId(USERID)).thenReturn(Optional.of(depositList));
        double balance = depositService.calculateUserBalance(USERID);
        assertEquals(150.0, balance);
    }
@Test
    public void calculateUserBalance_should_return_0_when_there_no_validate_gifts_and_meals() {
        LocalDate today = LocalDate.now();
        List<Deposit> depositList = List.of(
                new Deposit(GIFT, 50.0, today.minusDays(11), today.minusDays(6), USERID));
        when(depositRepository.findByUserId(USERID)).thenReturn(Optional.of(depositList));
        double balance = depositService.calculateUserBalance(USERID);
        assertEquals(0.0, balance);
    }
}
