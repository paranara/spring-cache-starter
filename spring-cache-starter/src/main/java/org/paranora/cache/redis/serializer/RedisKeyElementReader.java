package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.lang.Nullable;

import java.nio.ByteBuffer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/25 10:14
 */
public interface RedisKeyElementReader extends RedisElementReader<String> {

    @Nullable
    String read(ByteBuffer buffer,String name,KeyPrefix prefix);
}
