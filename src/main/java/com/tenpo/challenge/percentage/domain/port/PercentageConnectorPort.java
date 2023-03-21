package com.tenpo.challenge.percentage.domain.port;

import java.util.Optional;

public interface PercentageConnectorPort {

  Optional<Double> findPercentage();

}
