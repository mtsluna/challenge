package com.tenpo.challenge.calculation.domain.action.calculate;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;
import com.tenpo.challenge.percentage.domain.action.find.FindPercentageUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculateServiceTest {

  @InjectMocks
  private CalculateService calculateService;

  @Mock
  private FindPercentageUseCase findPercentageUseCase;

  @Test
  void calculate() throws ResourceNotFoundException {

    var x = 5;
    var y = 5;

    Mockito.when(this.findPercentageUseCase.findPercentage())
        .thenReturn(10.0);

    Double result = this.calculateService.calculate(x, y);

    Assertions.assertEquals(11, result);

  }
}