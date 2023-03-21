package com.tenpo.challenge._shared.cache.redis;

import com.tenpo.challenge._shared.constants.CacheConstants;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class JedisConfiguration {

  private final RedisConfigurationProperties redisConfigurationProperties;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(this.redisConfigurationProperties.getHost());
    redisStandaloneConfiguration.setPort(this.redisConfigurationProperties.getPort());

    JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration
        .builder()
        .connectTimeout(Duration.ofSeconds(CacheConstants.DEFAULT_TIMEOUT_IN_SECONDS))
        .build();

    return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
  }

  @Bean
  RedisTemplate<Object, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory);
    return redisTemplate;
  }

  @Bean
  @Primary
  RedisCacheManager cacheManagerJedis(
      JedisConnectionFactory jedisConnectionFactory,
      RedisCacheConfiguration redisCacheConfiguration
  ) {
    return RedisCacheManager
        .RedisCacheManagerBuilder
        .fromConnectionFactory(jedisConnectionFactory)
        .cacheDefaults(redisCacheConfiguration)
        .build();
  }

  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofSeconds(CacheConstants.DEFAULT_TTL_IN_SECONDS))
        .serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  }

}
