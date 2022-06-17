package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class JdkStringRedisSerializer extends StringRedisSerializer {

    protected JdkSerializationRedisSerializer jdkSerializationRedisSerializer;

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
