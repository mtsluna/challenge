package com.tenpo.challenge.calculation.domain.action.calculate;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;

public interface CalculateUseCase {

  double calculate(double x, double y) throws ResourceNotFoundException;

}
