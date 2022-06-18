package org.paranora.cache.redis.serializer;


/**
 * The type Custom key serialization pair.
 */
public class CustomKeySerializationPair extends CustomSerializationPair<String> implements KeySerializationPair{

    /**
     * Instantiates a new Custom key serialization pair.
     *
     * @param reader the reader
     * @param writer the writer
     */
    public CustomKeySerializationPair(RedisKeyElementReader reader, RedisKeyElementWriter writer) {
        super(reader, writer);
    }
}
