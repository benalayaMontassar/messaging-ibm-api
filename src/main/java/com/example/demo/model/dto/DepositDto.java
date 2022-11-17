package com.example.demo.model.dto;

import java.time.LocalDate;

public class DepositDto {
    private String type;
    private double amount;
    private String distributionDate;
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDistributionDate(String distributionDate) {
        this.distributionDate = distributionDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDistributionDate() {
        return distributionDate;
    }

    public String getUserId() {
        return userId;
    }
}
