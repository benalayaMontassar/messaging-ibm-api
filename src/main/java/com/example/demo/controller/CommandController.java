package com.example.demo.controller;

import com.example.demo.CommandDTO;
import com.example.demo.model.Command;
import com.example.demo.service.CommandService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command")
public class CommandController {
  @Autowired
  private final CommandService commandService;

  public CommandController(CommandService commandService) {
    this.commandService = commandService;
  }

  @PostMapping
  public ResponseEntity<Command> saveCommand(@Valid @RequestBody Command command) {
    return ResponseEntity.status(HttpStatus.CREATED).body(commandService.saveCommand(command));
  }

  @GetMapping("/{idRecipient}")
  public List<Command> getAllCommands(@PathVariable String idRecipient) {
    return commandService.getAllCommandsByRecipient(idRecipient);
  }

  @DeleteMapping("/{id}")
  public void deleteCommand(@PathVariable String id){
    commandService.deleteCommand(id);
  }
}


