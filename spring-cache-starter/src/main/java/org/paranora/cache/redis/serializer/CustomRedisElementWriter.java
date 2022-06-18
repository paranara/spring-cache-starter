package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

import java.nio.ByteBuffer;

/**
 * The type Custom redis element writer.
 *
 * @param <T> the type parameter
 */
public class CustomRedisElementWriter<T> implements RedisElementWriter<T> {

    /**
     * The Serializer.
     */
    @Nullable
    protected RedisSerializer<T> serializer;

    /**
     * Instantiates a new Custom redis element writer.
     *
     * @param serializer the serializer
     */
    public CustomRedisElementWriter(RedisSerializer<T> serializer) {
        this.serializer = serializer;
    }

    @Override
    public ByteBuffer write(T value) {

        if (serializer != null && (value == null || serializer.canSerialize(value.getClass()))) {
            return ByteBuffer.wrap(serializer.serialize(value));
        }

        if (value instanceof byte[]) {
            return ByteBuffer.wrap((byte[]) value);
        }

        if (value instanceof ByteBuffer) {
            return (ByteBuffer) value;
        }

        throw new IllegalStateException(
                String.format("Cannot serialize value of type %s without a serializer", value.getClass()));

    }
}