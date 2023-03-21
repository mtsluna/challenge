package com.tenpo.challenge._shared.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpStatusCodeException;

@Configuration
@EnableRetry
public class RetryConfiguration {

  @Bean
  public RetryTemplate retryTemplate() {
    return RetryTemplate.builder()
        .maxAttempts(3)
        .retryOn(HttpStatusCodeException.class)
        .build();
  }

}
