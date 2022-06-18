package org.paranora.cache.configuration.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * The type Jedis configuration factory.
 */
public class JedisConfigurationFactory {

    /**
     * Create redis sentinel connection factory redis connection factory.
     *
     * @param redis the redis
     * @return the redis connection factory
     */
    public RedisConnectionFactory createRedisSentinelConnectionFactory(final RedisProperties redis) {
        final JedisPoolConfig poolConfig = redis.getPool() != null ? preparejedisPoolConfig(redis) : new JedisPoolConfig();
        final JedisConnectionFactory factory = new JedisConnectionFactory(RedisConfigurationFactory.prepareSentinelConfig(redis), poolConfig);
        return prepareJedisConnectionFactory(redis,factory);

    }

    /**
     * Create redis cluster configuration factory redis connection factory.
     *
     * @param redis the redis
     * @return the redis connection factory
     */
    public static RedisConnectionFactory createRedisClusterConfigurationFactory(final RedisProperties redis) {
        final JedisPoolConfig poolConfig = redis.getPool() != null ? preparejedisPoolConfig(redis) : new JedisPoolConfig();
        final JedisConnectionFactory factory = new JedisConnectionFactory(RedisConfigurationFactory.prepareClusterConfig(redis), poolConfig);
        return prepareJedisConnectionFactory(redis,factory);
    }

    private static JedisConnectionFactory prepareJedisConnectionFactory(RedisProperties redis, JedisConnectionFactory factory) {
        factory.setHostName(redis.getHost());
        factory.setPort(redis.getPort());
        if (redis.getPassword() != null) {
            factory.setPassword(redis.getPassword());
        }
        factory.setDatabase(redis.getDatabase());
        if (redis.getTimeout() > 0) {
            factory.setTimeout(redis.getTimeout());
        }
        factory.setUseSsl(redis.isUseSsl());
        factory.setUsePool(redis.isUsePool());

        return factory;
    }

    private static JedisPoolConfig preparejedisPoolConfig(final RedisProperties redis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config=RedisConfigurationFactory.preparePoolConfig(redis,config);
        return config;
    }


}
