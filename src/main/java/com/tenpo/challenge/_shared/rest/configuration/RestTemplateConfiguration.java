package com.tenpo.challenge._shared.rest.configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    restTemplateBuilder.setConnectTimeout(Duration.of(5, ChronoUnit.SECONDS));
    restTemplateBuilder.setReadTimeout(Duration.of(5, ChronoUnit.SECONDS));

    return restTemplateBuilder.build();
  }
}
