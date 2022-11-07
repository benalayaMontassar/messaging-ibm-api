package com.example.demo.service.impl;

import com.example.demo.model.Command;
import com.example.demo.repository.CommandRepository;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.service.CommandService;
import com.mongodb.lang.NonNull;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements CommandService {

  private final CommandRepository commandRepository;
  private final RecipientRepository recipientRepository;


  @Autowired
  public CommandServiceImpl(CommandRepository commandRepository, RecipientRepository recipientRepository) {
    this.commandRepository = commandRepository;
    this.recipientRepository = recipientRepository;
  }


  @Override
  public Command saveCommand(@Valid Command commandToSave) {
   /* if (!commandToSave.isValidDeliveryDate(commandToSave.getDeliveryDate())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } else {*/
      //calculateCommandPrice(commandToSave);
   //   recipientRepository.save(commandToSave.getRecipient());
    return commandRepository.save(commandToSave);

  }

  private void calculateCommandPrice(Command commandToSave) {
    commandToSave.setPrice(commandToSave.getQuantity() * 2.5);

  }

  @Override
  public void deleteCommand(String id) {
     commandRepository.deleteById(id);
  }

  @Override
  public List<Command> getAllCommandsByRecipient(String recipientId) {
    return commandRepository.findByRecipientId(recipientId);
  }
}
