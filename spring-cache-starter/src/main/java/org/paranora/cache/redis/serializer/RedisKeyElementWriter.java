package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.util.Assert;

import java.nio.ByteBuffer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/24 18:07
 */
public interface RedisKeyElementWriter extends RedisElementWriter<String> {

    ByteBuffer write(KeyPrefix prefix,String name,String key);

    static RedisKeyElementWriter from(RedisKeySerializer serializer) {

        Assert.notNull(serializer, "Serializer must not be null!");
        return new CustomRedisKeyElementWriter(serializer);
    }
}
