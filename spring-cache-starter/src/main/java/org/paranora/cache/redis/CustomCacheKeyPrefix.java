package org.paranora.cache.redis;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CustomCacheKeyPrefix implements KeyPrefix, Serializable {

    protected String prefix;
    protected String delimiter;

    public CustomCacheKeyPrefix() {
        this.delimiter = ":";
    }

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
