package com.ebay.cabasservice.client;

import com.ebay.cabasservice.data.CitizenList;
import com.ebay.cabasservice.model.Citizen;
import com.ebay.cabasservice.service.ConfigurationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Citizen client responsible for contacting the citizen service */
@Service
public class CitizenClient {
  private static final String URL_PREFIX = "http://localhost:";
  private static final String URL_SUFFIX_GET_BY_AREA_NUMBER = "/citizen/getByAreaNumbers";
  private static final String CITIZEN_SERVICE_PORT = "citizenServicePort";
  private static final Logger LOGGER = LogManager.getLogger(CitizenClient.class);
  @Autowired private RestTemplate restTemplate;
  @Autowired private ConfigurationService configurationService;

  /**
   * get citizen by area number list
   *
   * @param areaNumbers list of area number
   * @return list of citizens
   */
  public List<Citizen> getCitizensByAreaNumbers(List<Integer> areaNumbers) {

    LOGGER.info("gets citizen service port from config server and build url");
    String citizenServicePort = this.configurationService.getPort(CITIZEN_SERVICE_PORT);
    String url = URL_PREFIX + citizenServicePort + URL_SUFFIX_GET_BY_AREA_NUMBER;

    String urlTemplate =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("areaNumbers", "{areaNumbers}")
            .encode()
            .toUriString();

    String listOfNumbers =
        areaNumbers.stream().map(Object::toString).collect(Collectors.joining(","));
    LOGGER.info("Send get request to citizen service to get citizen list");
    Optional<CitizenList> citizenList =
        Optional.ofNullable(
            restTemplate.getForObject(urlTemplate, CitizenList.class, listOfNumbers));
    if (citizenList.isEmpty()) {
      LOGGER.error("Gets null citizenList from citizen service");
      return new ArrayList<>();
    }
    return citizenList.get().getCitizenList();
  }
}
