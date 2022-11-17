package com.example.demo.service.impl;

import com.example.demo.model.Deposit;
import com.example.demo.repository.DepositRepository;
import com.example.demo.service.DepositService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DepositServiceImpl implements DepositService {
    private final static String GIFT = "gift";
    private final static String MEAL = "meal";
    private final DepositRepository depositRepository;

    public DepositServiceImpl(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public Deposit createDeposit(String type, double amount, LocalDate distributionDate, String userId) {
        LocalDate expirationDate;
        if (type.equals(GIFT)) {
            expirationDate = distributionDate.plusDays(365);
        } else if (type.equals(MEAL)) {
            expirationDate = LocalDate.of(distributionDate.getYear() + 1, 2, 28);
        } else {
            throw new IllegalArgumentException("Type de dépôt est invalid");
        }
        Deposit deposit = new Deposit(type, amount, distributionDate, expirationDate, userId);
        return depositRepository.save(deposit);
    }

    @Override
    public double calculateUserBalance(String userId) {
        Optional<List<Deposit>> deposits = depositRepository.findByUserId(userId);
        return deposits.map(depositList -> depositList.stream().filter(deposit -> deposit.getExpirationDate().isAfter(LocalDate.now()))
                .mapToDouble(Deposit::getAmount).sum()).orElse(0.0);
    }
}
