package com.tenpo.challenge.calculation.adapters.inbound.api.controller;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;
import com.tenpo.challenge.calculation.adapters.inbound.api.communication.CalculationCommunication;
import com.tenpo.challenge.calculation.adapters.inbound.api.presentation.CalculationPresentation;
import com.tenpo.challenge.calculation.domain.action.calculate.CalculateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/calculations")
@Validated
public class CalculationController {

  private final CalculateUseCase calculateUseCase;

  @PostMapping
  public ResponseEntity<CalculationPresentation> calculate(@RequestBody @Valid CalculationCommunication calculationCommunication)
      throws ResourceNotFoundException {
    double result = this.calculateUseCase.calculate(
        calculationCommunication.getX(),
        calculationCommunication.getY()
    );

    return ResponseEntity.ok(CalculationPresentation
        .builder()
        .result(result)
        .build()
    );
  }

}
