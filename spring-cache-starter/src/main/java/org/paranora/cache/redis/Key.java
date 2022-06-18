package org.paranora.cache.redis;

import lombok.Builder;
import lombok.Data;


/**
 * The type Key.
 */
@Data
@Builder
public class Key {
    private CustomCacheKeyPrefix prifix;
    private String content;
}
