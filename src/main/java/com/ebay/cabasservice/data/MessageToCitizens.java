package com.ebay.cabasservice.data;

import com.ebay.cabasservice.model.Citizen;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MessageToCitizens {

  List<Citizen> citizens;
  String message;
}
