package com.tenpo.challenge.percentage.domain.action.find;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;
import com.tenpo.challenge.percentage.domain.model.Percentage;
import com.tenpo.challenge.percentage.domain.port.PercentageConnectorPort;
import com.tenpo.challenge.percentage.domain.port.PercentageRepositoryPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindPercentageService implements FindPercentageUseCase {

  private final PercentageRepositoryPort percentageRepositoryPort;

  private final PercentageConnectorPort percentageConnectorPort;

  @Override
  public double findPercentage() throws ResourceNotFoundException {
    Optional<Double> percentageOptional = this
        .percentageConnectorPort
        .findPercentage();

    if(percentageOptional.isPresent()) {
      Double percentage = percentageOptional.get();

      log.debug("Percentage found in connector with value: {}", percentage);

      this.percentageRepositoryPort.save(
          Percentage
            .builder()
            .percentage(percentage)
            .build()
      );

      return percentage;
    }

    return this.percentageRepositoryPort.findLast().getPercentage();
  }
}
