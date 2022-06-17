package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.*;
import org.springframework.util.Assert;

import java.nio.ByteBuffer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/24 17:50
 */
public interface KeySerializationPair extends RedisSerializationContext.SerializationPair<String> {

    default RedisKeyElementWriter getKeyWriter() {
        return (RedisKeyElementWriter) getWriter();
    }

    default RedisKeyElementReader getKeyReader() {
        return (RedisKeyElementReader) getWriter();
    }

    default ByteBuffer write(KeyPrefix prefix,String name,String key) {
        return getKeyWriter().write(prefix,name,key);
    }

    default String read(ByteBuffer buffer,String name, KeyPrefix prefix) {
        return getKeyReader().read(buffer, name,prefix);
    }

    static KeySerializationPair fromSerializer(RedisKeySerializer serializer) {
        Assert.notNull(serializer, "RedisSerializer must not be null!");
        return new RedisKeySerializerToSerializationPairAdapter(serializer);
    }
}