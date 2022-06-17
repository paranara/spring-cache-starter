package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.nio.ByteBuffer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/24 18:24
 */
public class RedisKeySerializerToSerializationPairAdapter  implements KeySerializationPair {

    private final static RedisKeySerializerToSerializationPairAdapter BYTE_BUFFER = new RedisKeySerializerToSerializationPairAdapter(null);

    private final CustomKeySerializationPair pair;

    public RedisKeySerializerToSerializationPairAdapter(@Nullable RedisKeySerializer serializer) {
        pair = new CustomKeySerializationPair(new CustomRedisKeyElementReader(serializer), new CustomRedisKeyElementWriter(serializer));
    }

    @SuppressWarnings("unchecked")
    @Deprecated
    static <T> RedisSerializationContext.SerializationPair<T> raw() {
        return (RedisSerializationContext.SerializationPair) byteBuffer();
    }


    static RedisSerializationContext.SerializationPair<ByteBuffer> byteBuffer() {
        return (RedisSerializationContext.SerializationPair) BYTE_BUFFER;
    }

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
