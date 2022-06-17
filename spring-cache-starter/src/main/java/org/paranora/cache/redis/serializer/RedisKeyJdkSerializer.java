package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.Key;

public class RedisKeyJdkSerializer extends RedisKeySerializerAbs implements RedisKeySerializer {

    @Override
    protected byte[] serializeKeyPrifix(Key key) {
        return stringSerializer.serialize(key.getPrifix().toString());
    }

    @Override
    protected byte[] serializeKeyContent(Key key) {
        return jdkSerializer.serialize(key.getContent());
    }

    @Override
    public String deserialize(byte[] bytes) {
        return (String)jdkSerializer.deserialize(bytes);
    }
}
