package org.paranora.cache.redis.serializer;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/24 18:38
 */
public class CustomKeySerializationPair extends CustomSerializationPair<String> implements KeySerializationPair{

    public CustomKeySerializationPair(RedisKeyElementReader reader, RedisKeyElementWriter writer) {
        super(reader, writer);
    }
}
