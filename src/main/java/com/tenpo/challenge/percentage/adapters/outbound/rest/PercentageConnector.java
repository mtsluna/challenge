package com.tenpo.challenge.percentage.adapters.outbound.rest;

import com.tenpo.challenge.percentage.adapters.outbound.rest.configuration.PercentageConnectionProperties;
import com.tenpo.challenge.percentage.adapters.outbound.rest.dto.PercentageDto;
import com.tenpo.challenge.percentage.domain.port.PercentageConnectorPort;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PercentageConnector implements PercentageConnectorPort {

  private final RestTemplate restTemplate;
  private final PercentageConnectionProperties percentageConnectionProperties;

  @Override
  @Retryable(retryFor = RestClientResponseException.class)
  @Cacheable(cacheNames = "percentage")
  public Optional<Double> findPercentage() {
    log.debug(
        "Trying to get percentage from: %s/%s"
            .formatted(
                this.percentageConnectionProperties.getUrl(),
                this.percentageConnectionProperties.getPath()
            )
    );

    try {
      return Optional.of(
          Objects.requireNonNull(this.restTemplate.getForObject(
          "%s/%s"
              .formatted(
                  this.percentageConnectionProperties.getUrl(),
                  this.percentageConnectionProperties.getPath()
              ),
          PercentageDto.class
      )).getPercentage());
    } catch (RestClientException e) {
      log.error("Something went wrong went try to get percentage from resource", e);
      return Optional.empty();
    }
  }
}
