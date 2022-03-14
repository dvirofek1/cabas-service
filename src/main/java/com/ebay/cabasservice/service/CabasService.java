package com.ebay.cabasservice.service;

import com.ebay.cabasservice.client.CitizenClient;
import com.ebay.cabasservice.client.ConfigurationClient;
import com.ebay.cabasservice.client.MessageClient;
import com.ebay.cabasservice.data.RequestList;
import com.ebay.cabasservice.model.Citizen;
import com.ebay.cabasservice.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Responsible for providing service to CABAS */
@Service
public class CabasService {
  private static final Logger LOGGER = LogManager.getLogger(CabasService.class);
  @Autowired private CitizenClient citizenClient;
  @Autowired private MessageClient messageClient;
  @Autowired private ConfigurationClient configurationClient;
  @Autowired private ConfigurationService configurationService;

  /**
   * Send message to citizens
   *
   * @param areaNumbers list of area numbers
   * @param messageFormatType message format
   * @return list of requests
   */
  public List<Request> sendAlert(List<Integer> areaNumbers, int messageFormatType) {
    LOGGER.info("Gets citizens to send alerts");
    List<Citizen> citizens = citizenClient.getCitizensByAreaNumbers(areaNumbers);
    LOGGER.info("Checks if there are citizens or null");
    if (Objects.isNull(citizens) || citizens.isEmpty()) return new ArrayList<Request>();

    String message = "null";
    try {
      message = configurationService.getMessage(messageFormatType);
    } catch (JSONException e) {
      e.printStackTrace();
      LOGGER.error("get message from configuration service was failed");
      //
      return new ArrayList<>();
    }

    LOGGER.info("Send alert to citizens");
    Optional<RequestList> ans = Optional.ofNullable(messageClient.sendAlert(citizens, message));

    LOGGER.info("Checks if response from message client are not null");
    if (ans.isEmpty()) return new ArrayList<>();

    return ans.get().getRequestList();
  }
}
