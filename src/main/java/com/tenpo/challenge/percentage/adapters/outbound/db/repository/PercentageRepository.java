package com.tenpo.challenge.percentage.adapters.outbound.db.repository;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;
import com.tenpo.challenge.percentage.adapters.outbound.db.entity.PercentageEntity;
import com.tenpo.challenge.percentage.domain.model.Percentage;
import com.tenpo.challenge.percentage.domain.port.PercentageRepositoryPort;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PercentageRepository implements PercentageRepositoryPort {

  private final PercentageJpaRepository percentageJpaRepository;

  @Override
  public Percentage findLast() throws ResourceNotFoundException {

    PercentageEntity percentageEntity = this
        .percentageJpaRepository
        .findLastPercentage()
        .orElseThrow(() -> new ResourceNotFoundException("Last percentage not found!"));

    log.debug(
        "Percentage found in DB with id: {} and value: {}",
        percentageEntity.getId(),
        percentageEntity.getPercentage()
    );

    return Percentage
        .builder()
        .percentage(percentageEntity.getPercentage())
        .build();
  }

  @Async
  @Override
  public CompletableFuture<Percentage> save(Percentage percentage) {
    log.debug("Save async initialized with value: {}", percentage.getPercentage());

    PercentageEntity percentageEntity = this.percentageJpaRepository
        .save(PercentageEntity
            .builder()
            .percentage(percentage.getPercentage())
            .build());

    log.debug("Save async finished with id: {}", percentageEntity.getId());

    return CompletableFuture.completedFuture(Percentage
        .builder()
        .id(percentageEntity.getId())
        .percentage(percentageEntity.getPercentage())
        .build());
  }
}
