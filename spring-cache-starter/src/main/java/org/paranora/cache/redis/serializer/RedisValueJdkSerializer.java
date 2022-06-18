package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;


/**
 * The type Redis value jdk serializer.
 */
public class RedisValueJdkSerializer extends JdkSerializationRedisSerializer implements RedisValueSerializer {
}
