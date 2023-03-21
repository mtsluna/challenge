package com.tenpo.challenge.percentage.adapters.outbound.rest;

import static org.junit.jupiter.api.Assertions.*;

import com.tenpo.challenge.percentage.adapters.outbound.rest.configuration.PercentageConnectionProperties;
import com.tenpo.challenge.percentage.adapters.outbound.rest.dto.PercentageDto;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class PercentageConnectorTest {

  @InjectMocks
  private PercentageConnector percentageConnector;

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private PercentageConnectionProperties percentageConnectionProperties;

  @Test
  void findPercentage() {

    Mockito.when(this.percentageConnectionProperties.getPath())
            .thenReturn("percentage");

    Mockito.when(this.percentageConnectionProperties.getUrl())
        .thenReturn("http://localhost:8083");

    Mockito.when(this.restTemplate.getForObject("http://localhost:8083/percentage", PercentageDto.class))
        .thenReturn(
            PercentageDto
                .builder()
                .percentage(10.0)
                .build()
        );

    Optional<Double> optionalPercentage = this.percentageConnector.findPercentage();

    Assertions.assertTrue(optionalPercentage.isPresent());
    Assertions.assertEquals(10.0, optionalPercentage.get());

  }

  @Test
  void findPercentageFailConnector() {

    Mockito.when(this.percentageConnectionProperties.getPath())
        .thenReturn("percentage");

    Mockito.when(this.percentageConnectionProperties.getUrl())
        .thenReturn("http://localhost:8083");

    Mockito.doThrow(new RestClientException("client error"))
        .when(this.restTemplate)
        .getForObject("http://localhost:8083/percentage", PercentageDto.class);

    Assertions.assertDoesNotThrow(() -> {
      Optional<Double> optional = this.percentageConnector.findPercentage();

      Assertions.assertNotNull(optional);
      Assertions.assertFalse(optional.isPresent());
    });

  }
}