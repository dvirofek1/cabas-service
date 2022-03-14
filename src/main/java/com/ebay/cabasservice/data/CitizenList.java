package com.ebay.cabasservice.data;

import com.ebay.cabasservice.model.Citizen;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CitizenList {
  private final List<Citizen> citizenList;

  public CitizenList() {
    citizenList = new ArrayList<>();
  }
}
