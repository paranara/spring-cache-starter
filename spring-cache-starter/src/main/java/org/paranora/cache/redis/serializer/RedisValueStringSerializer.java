package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * The type Redis value string serializer.
 */
public class RedisValueStringSerializer implements RedisValueSerializer {

    /**
     * The Serializer.
     */
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
