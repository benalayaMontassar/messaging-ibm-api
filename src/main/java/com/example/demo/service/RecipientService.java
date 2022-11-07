package com.example.demo.service;

import com.example.demo.model.Recipient;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RecipientService {

  Recipient saveRecipient(Recipient recipientToSave);

  void deleteRecipient(String id);

  List<Recipient> getAllRecipients();

}
