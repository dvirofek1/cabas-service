package com.ebay.cabasservice.service;

import com.ebay.cabasservice.client.CitizenClient;
import com.ebay.cabasservice.client.MessageClient;
import com.ebay.cabasservice.data.RequestList;
import com.ebay.cabasservice.model.Citizen;
import com.ebay.cabasservice.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CabasServiceTest {
  private static final Logger LOGGER = LogManager.getLogger(CabasServiceTest.class);
  private static List<Integer> areaNumbers;
  private static List<Citizen> citizenList1;
  private static List<Request> requestList;
  private static String message;
  @Autowired CabasService cabasService;
  @MockBean CitizenClient citizenClient;
  @MockBean ConfigurationService configurationService;
  @MockBean MessageClient messageClient;

  @BeforeAll
  static void init() {
    LOGGER.info("init lists");
    areaNumbers = new ArrayList<>();
    citizenList1 = new ArrayList<>();

    requestList = new ArrayList<>();

    message = "Hey you must to run";

    for (int i = 0; i < 10; i++) {
      Citizen citizen1 =
          new Citizen("123" + i, "dvir" + i, "benita" + i, "Jerusalem", "054260687" + i, "", "1");

      requestList.add(new Request(citizen1, message));
    }
  }

  @Test
  void sendAlert() throws JSONException {
    LOGGER.info("execute sendAlert test");
    areaNumbers.add(1);
    Mockito.when(citizenClient.getCitizensByAreaNumbers(areaNumbers)).thenReturn(citizenList1);

    Mockito.when(configurationService.getMessage(1)).thenReturn(message);
    Mockito.when(messageClient.sendAlert(citizenList1, message))
        .thenReturn(new RequestList(requestList));

    List<Request> requestListAns = cabasService.sendAlert(areaNumbers, 1);
    LOGGER.info("assert equals");
    for (int i = 0; i < requestList.size(); i++) {
      Assertions.assertEquals(
          requestListAns.get(i).getCitizen().getPrivateId(),
          requestList.get(i).getCitizen().getPrivateId());
    }
  }
}
