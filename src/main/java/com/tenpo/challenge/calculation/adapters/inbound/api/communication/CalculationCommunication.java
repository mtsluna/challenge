package com.tenpo.challenge.calculation.adapters.inbound.api.communication;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
public class CalculationCommunication {
  @NotNull Double x;
  @NotNull Double y;
}
