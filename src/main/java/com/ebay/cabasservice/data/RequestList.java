package com.ebay.cabasservice.data;

import com.ebay.cabasservice.model.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestList {
  private List<Request> requestList;
}
