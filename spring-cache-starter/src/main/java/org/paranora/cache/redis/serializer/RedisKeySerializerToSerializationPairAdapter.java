package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.nio.ByteBuffer;

/**
 * The type Redis key serializer to serialization pair adapter.
 */
public class RedisKeySerializerToSerializationPairAdapter  implements KeySerializationPair {

    private final static RedisKeySerializerToSerializationPairAdapter BYTE_BUFFER = new RedisKeySerializerToSerializationPairAdapter(null);

    private final CustomKeySerializationPair pair;

    /**
     * Instantiates a new Redis key serializer to serialization pair adapter.
     *
     * @param serializer the serializer
     */
    public RedisKeySerializerToSerializationPairAdapter(@Nullable RedisKeySerializer serializer) {
        pair = new CustomKeySerializationPair(new CustomRedisKeyElementReader(serializer), new CustomRedisKeyElementWriter(serializer));
    }

    /**
     * Raw redis serialization context . serialization pair.
     *
     * @param <T> the type parameter
     * @return the redis serialization context . serialization pair
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    static <T> RedisSerializationContext.SerializationPair<T> raw() {
        return (RedisSerializationContext.SerializationPair) byteBuffer();
    }


    /**
     * Byte buffer redis serialization context . serialization pair.
     *
     * @return the redis serialization context . serialization pair
     */
    static RedisSerializationContext.SerializationPair<ByteBuffer> byteBuffer() {
        return (RedisSerializationContext.SerializationPair) BYTE_BUFFER;
    }

    /**
     * From redis serialization context . serialization pair.
     *
     * @param redisSerializer the redis serializer
     * @return the redis serialization context . serialization pair
     */
    public static RedisSerializationContext.SerializationPair<String> from(RedisKeySerializer redisSerializer) {
        Assert.notNull(redisSerializer, "RedisSerializer must not be null!");
        return new RedisKeySerializerToSerializationPairAdapter(redisSerializer);
    }

    @Override
    public RedisElementReader<String> getReader() {
        return pair.getReader();
    }

    @Override
    public RedisElementWriter<String> getWriter() {
        return pair.getWriter();
    }

}
