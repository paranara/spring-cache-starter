package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.util.Assert;

import java.nio.ByteBuffer;


/**
 * The interface Redis key element writer.
 */
public interface RedisKeyElementWriter extends RedisElementWriter<String> {

    /**
     * Write byte buffer.
     *
     * @param prefix the prefix
     * @param name   the name
     * @param key    the key
     * @return the byte buffer
     */
    ByteBuffer write(KeyPrefix prefix,String name,String key);

    /**
     * From redis key element writer.
     *
     * @param serializer the serializer
     * @return the redis key element writer
     */
    static RedisKeyElementWriter from(RedisKeySerializer serializer) {

        Assert.notNull(serializer, "Serializer must not be null!");
        return new CustomRedisKeyElementWriter(serializer);
    }
}
