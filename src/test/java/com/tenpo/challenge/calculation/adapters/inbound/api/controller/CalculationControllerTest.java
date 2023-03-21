package com.tenpo.challenge.calculation.adapters.inbound.api.controller;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;
import com.tenpo.challenge.calculation.adapters.inbound.api.communication.CalculationCommunication;
import com.tenpo.challenge.calculation.adapters.inbound.api.presentation.CalculationPresentation;
import com.tenpo.challenge.calculation.domain.action.calculate.CalculateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {

  @InjectMocks
  private CalculationController calculationController;

  @Mock
  private CalculateUseCase calculateUseCase;

  @Test
  void calculate() throws ResourceNotFoundException {

    var x = 5.0;
    var y = 5.0;

    CalculationCommunication calculationCommunication = CalculationCommunication
        .builder()
        .x(x)
        .y(y)
        .build();

    Mockito.when(this.calculateUseCase.calculate(x, y))
        .thenReturn(11.0);

    ResponseEntity<CalculationPresentation> response = this.calculationController
        .calculate(calculationCommunication);

    Mockito.verify(this.calculateUseCase, Mockito.times(1))
            .calculate(x, y);

    Assertions.assertEquals(
        HttpStatus.OK,
        response.getStatusCode()
    );

    Assertions.assertEquals(
        CalculationPresentation
            .builder()
            .result(11.0)
            .build(),
        response.getBody()
    );

  }
}