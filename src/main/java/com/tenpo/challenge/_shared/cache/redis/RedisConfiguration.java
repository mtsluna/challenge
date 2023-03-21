package com.tenpo.challenge._shared.cache.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@Profile(value = {"local", "pod"})
public abstract class RedisConfiguration {}
