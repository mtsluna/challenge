package com.tenpo.challenge.percentage.domain.port;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;
import com.tenpo.challenge.percentage.domain.model.Percentage;
import java.util.concurrent.CompletableFuture;

public interface PercentageRepositoryPort {

  Percentage findLast() throws ResourceNotFoundException;

  CompletableFuture<Percentage> save(Percentage percentage);

}
