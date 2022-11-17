package com.example.demo.service;

import com.example.demo.model.Deposit;

import java.time.LocalDate;
import java.util.Optional;

public interface DepositService {
    public Deposit createDeposit(String type, double amount, LocalDate distributionDate, String userId);
    public double calculateUserBalance(String userId);
}
