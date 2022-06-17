package org.paranora.cache.redis;

import org.paranora.cache.redis.serializer.RedisKeySerializerToSerializationPairAdapter;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.util.ByteUtils;

public class CustomRedisCache extends RedisCache implements Cacher {

    protected KeyPrefix keyPrefix;

    public CustomRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig,KeyPrefix keyPrefix) {
        super(name, cacheWriter, cacheConfig);
        this.keyPrefix=keyPrefix;
    }

    @Override
    protected byte[] serializeCacheKey(String cacheKey) {
        return ByteUtils.getBytes(((RedisKeySerializerToSerializationPairAdapter) getCacheConfiguration().getKeySerializationPair()).write(this.keyPrefix, getName(), cacheKey));
    }

    @Override
    protected String createCacheKey(Object key) {
        String convertedKey = convertKey(key);
        return convertedKey;
    }

}
