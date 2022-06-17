package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.lang.Nullable;

import java.nio.ByteBuffer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/24 18:28
 */
public class CustomRedisElementReader <T> implements RedisElementReader<T> {

    @Nullable
    protected RedisSerializer<T> serializer;

    public CustomRedisElementReader(RedisSerializer<T> serializer) {
        this.serializer = serializer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T read(ByteBuffer buffer) {
        if (serializer == null) {
            return (T) buffer;
        }
        return serializer.deserialize(ByteUtils.extractBytes(buffer));
    }

}
