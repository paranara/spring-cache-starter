package org.paranora.cache.redis.serializer;

import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;

public class CustomSerializationPair<T> implements RedisSerializationContext.SerializationPair<T> {

    private final RedisElementReader<T> reader;
    private final RedisElementWriter<T> writer;

    @SuppressWarnings("unchecked")
    CustomSerializationPair(RedisElementReader<? extends T> reader, RedisElementWriter<? extends T> writer) {

        this.reader = (RedisElementReader) reader;
        this.writer = (RedisElementWriter) writer;
    }

    @Override
    public RedisElementReader<T> getReader() {
        return this.reader;
    }

    @Override
    public RedisElementWriter<T> getWriter() {
        return this.writer;
    }
}
