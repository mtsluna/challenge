package com.tenpo.challenge._shared.cache.redis;

import com.tenpo.challenge._shared.constants.CacheConstants;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import javax.cache.CacheManager;
import javax.cache.Caching;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = {"local", "pod"})
public class RedissonConfiguration {

  @Bean
  public Config config(RedisConfigurationProperties redisConfigurationProperties) {
    Config config = new Config();
    config.useSingleServer().setAddress(
        CacheConstants.CONNECTION_URI
            .formatted(redisConfigurationProperties.getHost(), redisConfigurationProperties.getPort())
    );
    return config;
  }

  @Bean(name = "redissonCacheManager")
  public CacheManager cacheManagerRedisson(Config config) {
    CacheManager manager = Caching.getCachingProvider().getCacheManager();
    manager.createCache(CacheConstants.CACHE_NAME, org.redisson.jcache.configuration.RedissonConfiguration.fromConfig(config));
    return manager;
  }

  @Bean
  ProxyManager<String> proxyManager(@Qualifier("redissonCacheManager") CacheManager cacheManager) {
    return new JCacheProxyManager<>(cacheManager.getCache(CacheConstants.CACHE_NAME));
  }

}
