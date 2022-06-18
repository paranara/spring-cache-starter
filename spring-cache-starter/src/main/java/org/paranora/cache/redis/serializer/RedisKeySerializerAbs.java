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
 * The type Redis key serializer abs.
 */
public abstract class RedisKeySerializerAbs implements RedisKeySerializer {

    /**
     * The String serializer.
     */
    protected StringRedisSerializer stringSerializer = new StringRedisSerializer();
    /**
     * The Jdk serializer.
     */
    protected JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();

    /**
     * The Key prefix.
     */
    @Autowired
    protected KeyPrefix keyPrefix;

    @Override
    public byte[] serialize(String key) throws SerializationException {
        return serialize(parseKey(key));
    }

    /**
     * Key prefix.
     *
     * @param keyPrefix the key prefix
     */
    public void keyPrefix(KeyPrefix keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    /**
     * Key prefix key prefix.
     *
     * @return the key prefix
     */
    public KeyPrefix keyPrefix() {
        return this.keyPrefix;
    }

    /**
     * Parse key key.
     *
     * @param key the key
     * @return the key
     */
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

    /**
     * Serialize key prifix byte [ ].
     *
     * @param key the key
     * @return the byte [ ]
     */
    protected abstract byte[] serializeKeyPrifix(Key key);

    /**
     * Serialize key content byte [ ].
     *
     * @param key the key
     * @return the byte [ ]
     */
    protected abstract byte[] serializeKeyContent(Key key);

    /**
     * Serialize byte [ ].
     *
     * @param key the key
     * @return the byte [ ]
     */
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
