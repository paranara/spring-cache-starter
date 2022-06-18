package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.util.Assert;

import java.nio.ByteBuffer;


/**
 * The type Custom redis key element reader.
 */
public class CustomRedisKeyElementReader extends CustomRedisElementReader<String> implements RedisKeyElementReader {

    /**
     * Instantiates a new Custom redis key element reader.
     *
     * @param serializer the serializer
     */
    public CustomRedisKeyElementReader(RedisKeySerializer serializer) {
        super(serializer);
    }

    @Override
    public String read(ByteBuffer buffer,String name, KeyPrefix prefix) {
        Assert.notNull(buffer,String.format("buffer is can not be null!"));
        return ((RedisKeySerializer)serializer).deserialize(ByteUtils.extractBytes(buffer),name,prefix);
    }
}
