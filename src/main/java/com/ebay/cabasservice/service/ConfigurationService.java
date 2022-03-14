package com.ebay.cabasservice.service;

import com.ebay.cabasservice.client.ConfigurationClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
  private static final Logger LOGGER = LogManager.getLogger(ConfigurationService.class);
  private static final String MESSAGE_FORMAT = "message_format_";
  @Autowired private ConfigurationClient configurationClient;

  /**
   * get message by message format
   *
   * @param messageFormatType message format
   * @return message
   * @throws JSONException
   */
  public String getMessage(int messageFormatType) throws JSONException {
    LOGGER.info("Send request to client to get message");
    return configurationClient.get(MESSAGE_FORMAT + messageFormatType);
  }

  /**
   * get port by key
   *
   * @param string key
   * @return port
   */
  public String getPort(String string) {
    LOGGER.info("Send request to client to get port");
    return configurationClient.get(string);
  }
}
