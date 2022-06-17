package org.paranora.cache.redis;

import org.paranora.cache.redis.serializer.KeySerializationPair;
import org.paranora.cache.redis.serializer.RedisKeySerializer;
import org.paranora.cache.redis.serializer.RedisValueSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheManagerFactory {

    public static CacheManager customCacheManager(RedisConnectionFactory redisConnectionFactory, CacheProperties properties) {
        RedisKeySerializer keySerializer = properties.getKeySerializer();
        RedisValueSerializer valueSerializer = properties.getValueSerializer();

        RedisSerializationContext.SerializationPair<String> keySerializationPair = KeySerializationPair.fromSerializer(keySerializer);
        RedisSerializationContext.SerializationPair<Object> valueSerializationPair = RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer);

        Duration ttl = Duration.ofSeconds(properties.getExprieTime());

        RedisCacheConfiguration defaultRedisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(keySerializationPair)
                .serializeValuesWith(valueSerializationPair)
                .computePrefixWith(properties.getCacheKeyPrefix())
                .entryTtl(ttl);

        Map<String, RedisCacheConfiguration> cacheConfigurations = new LinkedHashMap<>();
        for (String key : properties.getExprieTimes().keySet()) {
            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                    .defaultCacheConfig()
                    .serializeKeysWith(keySerializationPair)
                    .serializeValuesWith(valueSerializationPair)
                    .computePrefixWith(properties.getCacheKeyPrefix())
                    .entryTtl(Duration.ofSeconds(properties.getExprieTimes().get(key)));
            cacheConfigurations.put(key, redisCacheConfiguration);
        }

        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        CustomRedisCacheManager redisCacheManager = new CustomRedisCacheManager(redisCacheWriter
                , properties.getCacheKeyPrefix()
                , defaultRedisCacheConfiguration
                , cacheConfigurations
                , true);
        redisCacheManager.afterPropertiesSet();

        return redisCacheManager;
    }


}
