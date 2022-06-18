package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.lang.Nullable;

import java.nio.ByteBuffer;


/**
 * The type Custom redis element reader.
 *
 * @param <T> the type parameter
 */
public class CustomRedisElementReader <T> implements RedisElementReader<T> {

    /**
     * The Serializer.
     */
    @Nullable
    protected RedisSerializer<T> serializer;

    /**
     * Instantiates a new Custom redis element reader.
     *
     * @param serializer the serializer
     */
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
