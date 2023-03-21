package com.tenpo.challenge._shared.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CacheConstants {

  public static final String CONNECTION_URI = "redis://%s:%d";

  public static final String CACHE_NAME = "cache";

  public static final Integer DEFAULT_TTL_IN_SECONDS = 1800;

  public static final Integer DEFAULT_TIMEOUT_IN_SECONDS = 1800;

}
