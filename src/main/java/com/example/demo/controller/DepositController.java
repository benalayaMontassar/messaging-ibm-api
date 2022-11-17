package com.example.demo.controller;

import com.example.demo.model.Deposit;
import com.example.demo.model.dto.DepositDto;
import com.example.demo.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping
    public ResponseEntity<Deposit> createDeposit(@RequestBody DepositDto depositToCreate) {
        Deposit savedDeposit = depositService.createDeposit(depositToCreate.getType(), depositToCreate.getAmount(), LocalDate.parse(depositToCreate.getDistributionDate()), depositToCreate.getUserId());
        return  ResponseEntity.status(HttpStatus.CREATED).body(savedDeposit);
    }

    @GetMapping("/balance/{userId}")
    public double getUserBalance(@PathVariable String userId) {
        return depositService.calculateUserBalance(userId);
    }
}
