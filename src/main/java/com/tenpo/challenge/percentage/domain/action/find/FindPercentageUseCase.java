package com.tenpo.challenge.percentage.domain.action.find;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;

public interface FindPercentageUseCase {

  double findPercentage() throws ResourceNotFoundException;

}
