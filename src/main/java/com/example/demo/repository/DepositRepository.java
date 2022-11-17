package com.example.demo.repository;

import com.example.demo.model.Deposit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepository extends MongoRepository<Deposit, String> {
    Optional<List<Deposit>> findByUserId(String userId);
}
