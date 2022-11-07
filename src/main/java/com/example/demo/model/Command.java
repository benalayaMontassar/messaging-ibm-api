package com.example.demo.model;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document
public class Command {
  @Id
  private String id;
  @DBRef
  @NotNull(message = "heee")
  private Recipient recipient;
  private LocalDate deliveryDate;
  @NotNull
@Size(max = 1)
  private Double quantity;
  private Double price;

  public Recipient getRecipient() {
    return recipient;
  }

  public void setRecipient(Recipient recipient) {
    this.recipient = recipient;
  }
  public LocalDate getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(LocalDate deliveryDate) {
    this.deliveryDate = deliveryDate;
  }
  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public boolean isValidQuantity(Double quantity) {
    return 0 <= quantity && quantity <= 10000 && quantity % 25 == 0;
  }

  public boolean isValidDeliveryDate(LocalDate deliveryDate) {
    return deliveryDate.compareTo(LocalDate.now().plusWeeks(1)) >= 0;
  }
}
