package com.tenpo.challenge.percentage.adapters.outbound.db.repository;

import com.tenpo.challenge._shared.exception.definition.ResourceNotFoundException;
import com.tenpo.challenge.percentage.domain.model.Percentage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({PercentageRepository.class})
@ActiveProfiles("test")
class PercentageRepositoryTest {

  @Autowired
  private PercentageRepository percentageRepository;

  @Test
  @Sql("/scripts/percentage/find-last.sql")
  void findLast() throws ResourceNotFoundException {

    Percentage percentage = this.percentageRepository.findLast();

    Assertions.assertEquals(
        Percentage
            .builder()
            .percentage(18.0)
            .build(),
        percentage
    );

  }

  @Test
  void findLastNotFound() {

    Throwable throwable = Assertions.assertThrows(
        ResourceNotFoundException.class,
        () -> this.percentageRepository.findLast()
    );

    Assertions.assertEquals("Last percentage not found!", throwable.getMessage());

  }

  @Test
  void save() throws ExecutionException, InterruptedException {

    Percentage percentage = Percentage
        .builder()
        .percentage(18.0)
        .build();

    CompletableFuture<Percentage> percentageCompletableFuture = this.percentageRepository
        .save(percentage);

    Percentage percentageResult = percentageCompletableFuture.get();

    Assertions.assertNotNull(percentageResult);
    Assertions.assertNotNull(percentageResult.getId());

  }
}