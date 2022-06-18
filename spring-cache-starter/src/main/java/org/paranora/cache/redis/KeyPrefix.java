package org.paranora.cache.redis;

import org.springframework.data.redis.cache.CacheKeyPrefix;


/**
 * The interface Key prefix.
 */
public interface KeyPrefix extends CacheKeyPrefix {
    /**
     * Prefix string.
     *
     * @return the string
     */
    String prefix();

    /**
     * Delimiter string.
     *
     * @return the string
     */
    String delimiter();
}
