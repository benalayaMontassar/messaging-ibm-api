package com.example.demo;

import com.example.demo.model.Recipient;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CommandDTO {

  @NotNull
  @Valid
  private Recipient recipient;
  @NotNull
  @Valid
  private LocalDate deliveryDate;
  @NotNull

  private Double quantity;
  private Double price;

}
