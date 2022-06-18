package org.paranora.cache.redis;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * The type Custom cache key prefix.
 */
@Data
@Builder
public class CustomCacheKeyPrefix implements KeyPrefix, Serializable {

    /**
     * The Prefix.
     */
    protected String prefix;
    /**
     * The Delimiter.
     */
    protected String delimiter;

    /**
     * Instantiates a new Custom cache key prefix.
     */
    public CustomCacheKeyPrefix() {
        this.delimiter = ":";
    }

    /**
     * Instantiates a new Custom cache key prefix.
     *
     * @param keyPrefix    the key prefix
     * @param keyDelimiter the key delimiter
     */
    public CustomCacheKeyPrefix(String keyPrefix, String keyDelimiter) {
        this.prefix = keyPrefix;
        this.delimiter = keyDelimiter;
    }

    @Override
    public String compute(String cacheName) {
        return prefix + cacheName + delimiter;
    }

    @Override
    public String toString() {
        return String.format("%s%s", prefix, delimiter);
    }

    @Override
    public String prefix() {
        return this.prefix;
    }

    @Override
    public String delimiter() {
        return this.delimiter;
    }
}
