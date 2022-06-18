package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.*;
import org.springframework.util.Assert;

import java.nio.ByteBuffer;


/**
 * The interface Key serialization pair.
 */
public interface KeySerializationPair extends RedisSerializationContext.SerializationPair<String> {

    /**
     * Gets key writer.
     *
     * @return the key writer
     */
    default RedisKeyElementWriter getKeyWriter() {
        return (RedisKeyElementWriter) getWriter();
    }

    /**
     * Gets key reader.
     *
     * @return the key reader
     */
    default RedisKeyElementReader getKeyReader() {
        return (RedisKeyElementReader) getWriter();
    }

    /**
     * Write byte buffer.
     *
     * @param prefix the prefix
     * @param name   the name
     * @param key    the key
     * @return the byte buffer
     */
    default ByteBuffer write(KeyPrefix prefix,String name,String key) {
        return getKeyWriter().write(prefix,name,key);
    }

    /**
     * Read string.
     *
     * @param buffer the buffer
     * @param name   the name
     * @param prefix the prefix
     * @return the string
     */
    default String read(ByteBuffer buffer,String name, KeyPrefix prefix) {
        return getKeyReader().read(buffer, name,prefix);
    }

    /**
     * From serializer key serialization pair.
     *
     * @param serializer the serializer
     * @return the key serialization pair
     */
    static KeySerializationPair fromSerializer(RedisKeySerializer serializer) {
        Assert.notNull(serializer, "RedisSerializer must not be null!");
        return new RedisKeySerializerToSerializationPairAdapter(serializer);
    }
}