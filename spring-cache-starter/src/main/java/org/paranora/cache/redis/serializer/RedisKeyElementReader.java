package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.KeyPrefix;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.lang.Nullable;

import java.nio.ByteBuffer;


/**
 * The interface Redis key element reader.
 */
public interface RedisKeyElementReader extends RedisElementReader<String> {

    /**
     * Read string.
     *
     * @param buffer the buffer
     * @param name   the name
     * @param prefix the prefix
     * @return the string
     */
    @Nullable
    String read(ByteBuffer buffer,String name,KeyPrefix prefix);
}
