package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.data.redis.serializer.RedisSerializer;


/**
 * The interface Redis ele writer.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 */
public interface RedisEleWriter<T ,S extends RedisSerializer> extends RedisElementWriter<T> {
}
