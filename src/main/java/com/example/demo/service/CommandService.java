package com.example.demo.service;

import com.example.demo.model.Command;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface CommandService {

  Command saveCommand(Command commandToSave);

  void deleteCommand(String id);

  List<Command> getAllCommandsByRecipient(String recipientId);

}
