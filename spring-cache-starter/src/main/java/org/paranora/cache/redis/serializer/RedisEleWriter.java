package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/25 10:44
 */
public interface RedisEleWriter<T ,S extends RedisSerializer> extends RedisElementWriter<T> {
}
