package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.ByteBuffer;


/**
 * The type Custom redis key element writer.
 */
public class CustomRedisKeyElementWriter extends CustomRedisElementWriter<String> implements RedisKeyElementWriter {

    /**
     * Instantiates a new Custom redis key element writer.
     *
     * @param serializer the serializer
     */
    public CustomRedisKeyElementWriter(RedisKeySerializer serializer) {
        super(serializer);
    }

    @Override
    public ByteBuffer write(KeyPrefix prefix,String name,String key) {
        if (serializer != null && (key == null || serializer.canSerialize(key.getClass()))) {
            return ByteBuffer.wrap(((RedisKeySerializer)serializer).serialize(prefix,name,key));
        }
        throw new IllegalStateException(
                String.format("Cannot serialize value of type %s without a serializer", key.getClass()));
    }

}
