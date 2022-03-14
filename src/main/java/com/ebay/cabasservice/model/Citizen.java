package com.ebay.cabasservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {

  private String privateId;
  private String firstName;
  private String lastName;
  private String city;
  private String phone;
  private String guardianId;
  private String areaNumber;
}
