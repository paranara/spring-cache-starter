package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializationContext;


/**
 * The interface Value serialization pair.
 */
public interface ValueSerializationPair extends RedisSerializationContext.SerializationPair<Object> {
}
