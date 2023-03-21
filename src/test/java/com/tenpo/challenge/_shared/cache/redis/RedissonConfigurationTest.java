package com.tenpo.challenge._shared.cache.redis;

import static org.junit.jupiter.api.Assertions.*;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.OptionalFeature;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;
import javax.cache.spi.CachingProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.config.Config;

@ExtendWith(MockitoExtension.class)
class RedissonConfigurationTest {

  @Test
  void config() {

    RedissonConfiguration redissonConfiguration = new RedissonConfiguration();

    var redisConfigurationProperties = new RedisConfigurationProperties();
    redisConfigurationProperties.setHost("localhost");
    redisConfigurationProperties.setPort(6379);

    Config result = redissonConfiguration.config(redisConfigurationProperties);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.useSingleServer().getAddress(), "redis://localhost:6379");

  }

  CacheManager createCacheManager() {
    return new CacheManager () {

      private Map<String, Object> caches = new HashMap<>();

      @Override
      public CachingProvider getCachingProvider() {
        return null;
      }

      @Override
      public URI getURI() {
        return null;
      }

      @Override
      public ClassLoader getClassLoader() {
        return null;
      }

      @Override
      public Properties getProperties() {
        return null;
      }

      @Override
      public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String cacheName,
          C configuration) throws IllegalArgumentException {
        caches.put(cacheName, configuration);
        return null;
      }

      @Override
      public <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyType,
          Class<V> valueType) {
        return null;
      }

      @Override
      public <K, V> Cache<K, V> getCache(String cacheName) {
        return new Cache<K, V>() {
          @Override
          public V get(K key) {
            return null;
          }

          @Override
          public Map<K, V> getAll(Set<? extends K> keys) {
            return null;
          }

          @Override
          public boolean containsKey(K key) {
            return false;
          }

          @Override
          public void loadAll(Set<? extends K> keys, boolean replaceExistingValues,
              CompletionListener completionListener) {

          }

          @Override
          public void put(K key, V value) {

          }

          @Override
          public V getAndPut(K key, V value) {
            return null;
          }

          @Override
          public void putAll(Map<? extends K, ? extends V> map) {

          }

          @Override
          public boolean putIfAbsent(K key, V value) {
            return false;
          }

          @Override
          public boolean remove(K key) {
            return false;
          }

          @Override
          public boolean remove(K key, V oldValue) {
            return false;
          }

          @Override
          public V getAndRemove(K key) {
            return null;
          }

          @Override
          public boolean replace(K key, V oldValue, V newValue) {
            return false;
          }

          @Override
          public boolean replace(K key, V value) {
            return false;
          }

          @Override
          public V getAndReplace(K key, V value) {
            return null;
          }

          @Override
          public void removeAll(Set<? extends K> keys) {

          }

          @Override
          public void removeAll() {

          }

          @Override
          public void clear() {

          }

          @Override
          public <C extends Configuration<K, V>> C getConfiguration(Class<C> clazz) {
            return null;
          }

          @Override
          public <T> T invoke(K key, EntryProcessor<K, V, T> entryProcessor,
              Object... arguments) throws EntryProcessorException {
            return null;
          }

          @Override
          public <T> Map<K, EntryProcessorResult<T>> invokeAll(Set<? extends K> keys,
              EntryProcessor<K, V, T> entryProcessor, Object... arguments) {
            return null;
          }

          @Override
          public String getName() {
            return null;
          }

          @Override
          public CacheManager getCacheManager() {
            return null;
          }

          @Override
          public void close() {

          }

          @Override
          public boolean isClosed() {
            return false;
          }

          @Override
          public <T> T unwrap(Class<T> clazz) {
            return null;
          }

          @Override
          public void registerCacheEntryListener(
              CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {

          }

          @Override
          public void deregisterCacheEntryListener(
              CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {

          }

          @Override
          public Iterator<Entry<K, V>> iterator() {
            return null;
          }
        };
      }

      @Override
      public Iterable<String> getCacheNames() {
        return null;
      }

      @Override
      public void destroyCache(String cacheName) {

      }

      @Override
      public void enableManagement(String cacheName, boolean enabled) {

      }

      @Override
      public void enableStatistics(String cacheName, boolean enabled) {

      }

      @Override
      public void close() {

      }

      @Override
      public boolean isClosed() {
        return false;
      }

      @Override
      public <T> T unwrap(Class<T> clazz) {
        return null;
      }
    };
  }

  CachingProvider createCachingProvider() {
    return new CachingProvider() {
      @Override
      public CacheManager getCacheManager(URI uri, ClassLoader classLoader,
          Properties properties) {
        return null;
      }

      @Override
      public ClassLoader getDefaultClassLoader() {
        return null;
      }

      @Override
      public URI getDefaultURI() {
        return null;
      }

      @Override
      public Properties getDefaultProperties() {
        return null;
      }

      @Override
      public CacheManager getCacheManager(URI uri, ClassLoader classLoader) {
        return null;
      }

      @Override
      public CacheManager getCacheManager() {
        return createCacheManager();
      }

      @Override
      public void close() {

      }

      @Override
      public void close(ClassLoader classLoader) {

      }

      @Override
      public void close(URI uri, ClassLoader classLoader) {

      }

      @Override
      public boolean isSupported(OptionalFeature optionalFeature) {
        return false;
      }
    };
  }

  @Test
  void cacheManageRedisson() {

    RedissonConfiguration redissonConfiguration = new RedissonConfiguration();

    var redisConfigurationProperties = new RedisConfigurationProperties();
    redisConfigurationProperties.setHost("192.178.0.1");
    redisConfigurationProperties.setPort(6379);

    Config config = redissonConfiguration.config(redisConfigurationProperties);

    try (MockedStatic<Caching> utilities = Mockito.mockStatic(Caching.class)) {
      utilities.when(Caching::getCachingProvider).thenReturn(createCachingProvider());

      CacheManager cacheManager = redissonConfiguration.cacheManagerRedisson(config);

      Assertions.assertNotNull(cacheManager.getCache("name"));
    }

  }

  @Test
  void proxyManager() {
    RedissonConfiguration redissonConfiguration = new RedissonConfiguration();
    ProxyManager<String> proxyManager = redissonConfiguration.proxyManager(createCacheManager());
    Assertions.assertNotNull(proxyManager);
  }
}