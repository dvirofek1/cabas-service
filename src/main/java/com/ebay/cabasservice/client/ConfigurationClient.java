package com.ebay.cabasservice.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/** Configuration client responsible for contacting the configuration service */
@Service
public class ConfigurationClient {
  private static final Logger LOGGER = LogManager.getLogger(ConfigurationClient.class);
  private static final String URL = "http://localhost:9999/configuration/get";
  @Autowired private RestTemplate restTemplate;

  /**
   * get value by key
   *
   * @param key key
   * @return value
   */
  public String get(String key) {
    String urlTemplate =
        UriComponentsBuilder.fromHttpUrl(URL).queryParam("key", "{key}").encode().toUriString();
    Map<String, String> params = new HashMap<>();
    params.put("key", key);
    LOGGER.info("send request to service");
    return restTemplate.getForObject(urlTemplate, String.class, params);
  }
}
