package com.tenpo.challenge.calculation.domain.action.calculate;

import com.tenpo.challenge._shared.constants.CalculateConstants;
import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;
import com.tenpo.challenge.percentage.domain.action.find.FindPercentageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculateService implements CalculateUseCase {

  private final FindPercentageUseCase findPercentageUseCase;

  @Override
  public double calculate(double x, double y) throws ResourceNotFoundException {
    double percentage = this
        .findPercentageUseCase
        .findPercentage();

    log.debug(
        "Calculate service on going, got percentage: {} with the next inputs x: {}, y: {}",
        percentage,
        x,
        y
    );

    return (x + y) * (percentage / CalculateConstants.ONE_HUNDRED + CalculateConstants.ONE);
  }
}
