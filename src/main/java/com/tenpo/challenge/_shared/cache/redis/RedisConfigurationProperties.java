package com.tenpo.challenge._shared.cache.redis;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Profile(value = {"local", "pod"})
@Data
public class RedisConfigurationProperties {

  private String host;
  private int port;

}
