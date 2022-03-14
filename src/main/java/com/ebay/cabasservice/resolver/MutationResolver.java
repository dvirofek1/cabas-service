package com.ebay.cabasservice.resolver;

import com.ebay.cabasservice.model.Request;
import com.ebay.cabasservice.service.CabasService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MutationResolver implements GraphQLMutationResolver {

  @Autowired private CabasService cabasService;

  /**
   * Send alert to citizens by area numbers
   *
   * @param areaNumbers list of area numbers
   * @param messageFormatType message format
   * @return list of requests
   */
  public List<Request> sendAlert(List<Integer> areaNumbers, int messageFormatType) {
    return cabasService.sendAlert(areaNumbers, messageFormatType);
  }
}
