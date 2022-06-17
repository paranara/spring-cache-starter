package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/23 17:25
 */
public interface RedisKeySerializer extends RedisSerializer<String> {

    @Nullable
    byte[] serialize(@Nullable KeyPrefix prefix,@Nullable String name,@Nullable String key) throws SerializationException;

    String deserialize(@Nullable byte[] bytes,String name, KeyPrefix prefix) throws SerializationException;
}
