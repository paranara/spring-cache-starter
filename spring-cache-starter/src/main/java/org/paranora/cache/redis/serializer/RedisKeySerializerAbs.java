package org.paranora.cache.redis.serializer;

import org.paranora.cache.redis.Key;
import org.paranora.cache.redis.CustomCacheKeyPrefix;
import org.paranora.cache.redis.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/6/23 17:25
 */
public abstract class RedisKeySerializerAbs implements RedisKeySerializer {

    protected StringRedisSerializer stringSerializer = new StringRedisSerializer();
    protected JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();

    @Autowired
    protected KeyPrefix keyPrefix;

    @Override
    public byte[] serialize(String key) throws SerializationException {
        return serialize(parseKey(key));
    }

    public void keyPrefix(KeyPrefix keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public KeyPrefix keyPrefix() {
        return this.keyPrefix;
    }

    protected Key parseKey(String key) {
        int delimiterIndex = -1;

        if (!ObjectUtils.isEmpty(this.keyPrefix)
                && !ObjectUtils.isEmpty(this.keyPrefix().prefix())
                && !ObjectUtils.isEmpty(this.keyPrefix().delimiter())) {
            delimiterIndex = key.indexOf(this.keyPrefix().delimiter());
        }

        if (delimiterIndex < 0) {
            return Key.builder()
                    .prifix(CustomCacheKeyPrefix
                            .builder()
                            .delimiter("")
                            .prefix("")
                            .build())
                    .content(key)
                    .build();
        }

        String keyPrefixStr = key.substring(0, delimiterIndex);
        String keyContentStr = key.substring(delimiterIndex + this.keyPrefix().delimiter().length());
        return Key.builder()
                .prifix(CustomCacheKeyPrefix
                        .builder()
                        .delimiter(this.keyPrefix.delimiter())
                        .prefix(keyPrefixStr)
                        .build())
                .content(keyContentStr)
                .build();
    }

    protected abstract byte[] serializeKeyPrifix(Key key);

    protected abstract byte[] serializeKeyContent(Key key);

    protected byte[] serialize(Key key) {
        byte[] prefixBytes = serializeKeyPrifix(key);
        byte[] contentBytes = serializeKeyContent(key);

        byte[] prefixedKey = Arrays.copyOf(prefixBytes, prefixBytes.length + contentBytes.length);
        System.arraycopy(contentBytes, 0, prefixedKey, prefixBytes.length, contentBytes.length);
        return prefixedKey;
    }

    @Override
    public byte[] serialize(KeyPrefix prefix, String name, String key) throws SerializationException {
        Key caechKey=Key.builder()
                .prifix(CustomCacheKeyPrefix
                        .builder()
                        .delimiter(prefix.delimiter())
                        .prefix(String.format("%s%s",prefix.prefix(),name))
                        .build())
                .content(key)
                .build();
        return serialize(caechKey);
    }

    @Override
    public String deserialize(byte[] bytes, String name, KeyPrefix prefix) throws SerializationException {
        return deserialize(bytes);
    }
}
