package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

public interface RedisKeySerializer extends RedisSerializer<String> {

    /**
     * Serialize byte [ ].
     *
     * @param prefix the prefix
     * @param name   the name
     * @param key    the key
     * @return the byte [ ]
     * @throws SerializationException the serialization exception
     */
    @Nullable
    byte[] serialize(@Nullable KeyPrefix prefix,@Nullable String name,@Nullable String key) throws SerializationException;

    /**
     * Deserialize string.
     *
     * @param bytes  the bytes
     * @param name   the name
     * @param prefix the prefix
     * @return the string
     * @throws SerializationException the serialization exception
     */
    String deserialize(@Nullable byte[] bytes,String name, KeyPrefix prefix) throws SerializationException;
}
