package com.tenpo.challenge._shared.rate_limit;

import com.giffing.bucket4j.spring.boot.starter.config.cache.SyncCacheResolver;
import com.giffing.bucket4j.spring.boot.starter.config.cache.jcache.JCacheCacheResolver;
import javax.cache.CacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = {"local", "pod"})
public class Bucket4jConfiguration {

  @Bean
  public SyncCacheResolver bucket4jCacheResolver(@Qualifier("redissonCacheManager") CacheManager cacheManager) {
    return new JCacheCacheResolver(cacheManager);
  }

}
