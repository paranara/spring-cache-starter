package org.paranora.cache.redis;

import org.springframework.data.redis.cache.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * The type Custom redis cache manager.
 */
public class CustomRedisCacheManager extends RedisCacheManager {

    /**
     * The Redis cache writer.
     */
    protected RedisCacheWriter redisCacheWriter;
    /**
     * The Key prefix.
     */
    protected KeyPrefix keyPrefix;

    /**
     * Instantiates a new Custom redis cache manager.
     *
     * @param cacheWriter                the cache writer
     * @param keyPrefix                  the key prefix
     * @param defaultCacheConfiguration  the default cache configuration
     * @param initialCacheConfigurations the initial cache configurations
     * @param allowInFlightCacheCreation the allow in flight cache creation
     */
    public CustomRedisCacheManager(RedisCacheWriter cacheWriter,KeyPrefix keyPrefix, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
        this.redisCacheWriter=cacheWriter;
        this.keyPrefix=keyPrefix;
//        try {
//            Field field = RedisCacheConfiguration.class.getField("keyPrefix");
//            field.setAccessible(true);
//            this.keyPrefix = (KeyPrefix) field.get(defaultCacheConfiguration);
//        } catch (RuntimeException ex) {
//            throw ex;
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        return new CustomRedisCache(name, redisCacheWriter,cacheConfig,this.keyPrefix);
    }

}
