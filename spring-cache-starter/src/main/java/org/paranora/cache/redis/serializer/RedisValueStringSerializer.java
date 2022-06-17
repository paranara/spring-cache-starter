package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/25 15:53
 */
public class RedisValueStringSerializer implements RedisValueSerializer {

    protected StringRedisSerializer serializer=new StringRedisSerializer();

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        throw new UnsupportedOperationException();
    }
}
