package org.paranora.cache.redis;

import org.springframework.data.redis.cache.CacheKeyPrefix;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/24 18:10
 */
public interface KeyPrefix extends CacheKeyPrefix {
    String prefix();
    String delimiter();
}
