package com.example.demo.model;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document
@CompoundIndex(name = "recipient_unique_idx", def = "{'name': 1, 'address': 1, 'postalCode': 1, 'town': 1, 'country': 1}",
    unique = true)
public class Recipient {

  @Id
  private String id;
  @NotNull
  private String name;
  @NotNull
  private String address;
  @NotNull
  private String postalCode;
  @NotNull
  private String town;
  @NonNull
  private String country;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
