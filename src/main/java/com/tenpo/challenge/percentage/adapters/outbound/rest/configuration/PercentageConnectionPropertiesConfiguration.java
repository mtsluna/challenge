package com.tenpo.challenge.percentage.adapters.outbound.rest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PercentageConnectionPropertiesConfiguration {

  @Bean
  @ConfigurationProperties("connectors.percentage-service")
  public PercentageConnectionProperties percentageConnectionProperties() {
    return new PercentageConnectionProperties();
  }

}
