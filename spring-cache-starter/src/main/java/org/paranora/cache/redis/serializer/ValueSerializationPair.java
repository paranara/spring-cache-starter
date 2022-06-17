package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/24 17:53
 */
public interface ValueSerializationPair extends RedisSerializationContext.SerializationPair<Object> {
}
