package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.Key;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/24 10:16
 */
public class RedisKeyStringSerializer extends RedisKeySerializerAbs implements RedisKeySerializer {

    @Override
    protected byte[] serializeKeyPrifix(Key key) {
        return stringSerializer.serialize(key.getPrifix().toString());
    }

    @Override
    protected byte[] serializeKeyContent(Key key) {
        return stringSerializer.serialize(key.getContent());
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return stringSerializer.deserialize(bytes);
    }
}
