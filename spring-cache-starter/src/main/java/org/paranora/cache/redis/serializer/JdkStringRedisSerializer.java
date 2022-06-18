package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * The type Jdk string redis serializer.
 */
public class JdkStringRedisSerializer extends StringRedisSerializer {

    /**
     * The Jdk serialization redis serializer.
     */
    protected JdkSerializationRedisSerializer jdkSerializationRedisSerializer;

    /**
     * Instantiates a new Jdk string redis serializer.
     *
     * @param jdkSerializationRedisSerializer the jdk serialization redis serializer
     */
    public JdkStringRedisSerializer(JdkSerializationRedisSerializer jdkSerializationRedisSerializer){
        this.jdkSerializationRedisSerializer=jdkSerializationRedisSerializer;
    }

    @Override
    public String deserialize(byte[] bytes) {
        return (String)jdkSerializationRedisSerializer.deserialize(bytes);
    }

    @Override
    public byte[] serialize(String key) {
        return jdkSerializationRedisSerializer.serialize(key);
    }
}
