package com.tenpo.challenge.percentage.domain.action.find;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;
import com.tenpo.challenge.percentage.domain.model.Percentage;
import com.tenpo.challenge.percentage.domain.port.PercentageConnectorPort;
import com.tenpo.challenge.percentage.domain.port.PercentageRepositoryPort;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindPercentageServiceTest {

  @InjectMocks
  private FindPercentageService findPercentageService;

  @Mock
  private PercentageRepositoryPort percentageRepositoryPort;

  @Mock
  private PercentageConnectorPort percentageConnectorPort;

  @Test
  void findPercentage(){

    Mockito.when(this.percentageConnectorPort.findPercentage())
        .thenReturn(
            Optional.of(11.0)
        );

    Mockito.when(this.percentageRepositoryPort.save(Mockito.any()))
        .thenReturn(CompletableFuture.completedFuture(
            Percentage
                .builder()
                .id(1L)
                .percentage(11.0)
                .build()
        ));

    Assertions.assertDoesNotThrow(() -> {
      double result = this.findPercentageService.findPercentage();
      Assertions.assertEquals(11.0, result);
    });

    Mockito.verify(this.percentageConnectorPort, Mockito.times(1))
        .findPercentage();

    Mockito.verify(this.percentageRepositoryPort, Mockito.times(1))
        .save(Mockito.any());

  }

  @Test
  void findPercentageOnErrorOnConnector() throws ResourceNotFoundException {

    Mockito.when(this.percentageConnectorPort.findPercentage())
        .thenReturn(
            Optional.empty()
        );

    Mockito.when(this.percentageRepositoryPort.findLast())
        .thenReturn(Percentage.builder().percentage(11.0).build());

    Assertions.assertDoesNotThrow(() -> {
      double result = this.findPercentageService.findPercentage();
      Assertions.assertEquals(11.0, result);
    });

    Mockito.verify(this.percentageConnectorPort, Mockito.times(1))
        .findPercentage();

    Mockito.verify(this.percentageRepositoryPort, Mockito.times(1))
        .findLast();

  }
}