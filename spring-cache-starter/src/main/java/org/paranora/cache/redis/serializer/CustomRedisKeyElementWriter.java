package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.ByteBuffer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/25 10:18
 */
public class CustomRedisKeyElementWriter extends CustomRedisElementWriter<String> implements RedisKeyElementWriter {

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
