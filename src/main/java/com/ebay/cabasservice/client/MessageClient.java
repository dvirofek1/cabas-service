package com.ebay.cabasservice.client;

import com.ebay.cabasservice.data.MessageToCitizens;
import com.ebay.cabasservice.data.RequestList;
import com.ebay.cabasservice.model.Citizen;
import com.ebay.cabasservice.service.ConfigurationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/** Message client responsible for contacting the message service */
@Service
public class MessageClient {
  public static final String MESSAGE_SERVICE_PORT = "messageServicePort";
  private static final String SEND_ALERT_URL_PREFIX = "http://localhost:";
  private static final String SEND_ALERT_URL_SUFFIX = "/message/sendAlert";
  private static final Logger LOGGER = LogManager.getLogger(ConfigurationClient.class);
  @Autowired RestTemplate restTemplate;
  @Autowired private ConfigurationService configurationService;

  /**
   * Send message to citizens
   *
   * @param citizens list of citizens
   * @param message message
   * @return Request list that represents the citizens and message
   */
  public RequestList sendAlert(List<Citizen> citizens, String message) {

    String messageServicePort = this.configurationService.getPort(MESSAGE_SERVICE_PORT);
    String url = SEND_ALERT_URL_PREFIX + messageServicePort + SEND_ALERT_URL_SUFFIX;
    LOGGER.info("Send alert");
    return this.restTemplate.postForObject(
        url, new MessageToCitizens(citizens, message), RequestList.class);
  }
}
