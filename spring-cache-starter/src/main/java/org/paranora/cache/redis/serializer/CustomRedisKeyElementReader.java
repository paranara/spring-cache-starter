package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.util.Assert;

import java.nio.ByteBuffer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/25 10:38
 */
public class CustomRedisKeyElementReader extends CustomRedisElementReader<String> implements RedisKeyElementReader {

    public CustomRedisKeyElementReader(RedisKeySerializer serializer) {
        super(serializer);
    }

    @Override
    public String read(ByteBuffer buffer,String name, KeyPrefix prefix) {
        Assert.notNull(buffer,String.format("buffer is can not be null!"));
        return ((RedisKeySerializer)serializer).deserialize(ByteUtils.extractBytes(buffer),name,prefix);
    }
}
