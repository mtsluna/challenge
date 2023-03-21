package com.tenpo.challenge._shared.cache.redis;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redisson.redis")
@Data
public class RedisConfigurationProperties {

  private String host;
  private int port;

}
