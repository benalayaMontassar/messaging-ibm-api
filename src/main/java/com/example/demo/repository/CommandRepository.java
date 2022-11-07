package com.example.demo.repository;

import com.example.demo.model.Command;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface CommandRepository extends MongoRepository<Command, String> {

  @Query("{ 'recipient.id' : ?0 }")
  List<Command> findByRecipientId(String recipientId);
}


