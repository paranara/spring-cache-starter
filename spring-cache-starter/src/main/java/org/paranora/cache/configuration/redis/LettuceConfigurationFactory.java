package org.paranora.cache.configuration.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import java.time.Duration;

/**
 * The type Lettuce configuration factory.
 */
public class LettuceConfigurationFactory {

    /**
     * Create redis standalone configuration redis connection factory.
     *
     * @param redis the redis
     * @return the redis connection factory
     */
    public static RedisConnectionFactory createRedisStandaloneConfiguration(final RedisProperties redis)     {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redis.getHost());
        configuration.setPort(redis.getPort());
        configuration.setDatabase(redis.getDatabase());
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
        return factory;
    }

    /**
     * Create redis sentinel connection factory redis connection factory.
     *
     * @param redis the redis
     * @return the redis connection factory
     */
    public static RedisConnectionFactory createRedisSentinelConnectionFactory(final RedisProperties redis) {
        final LettucePoolingClientConfiguration lettucePoolingClientConfiguration = prepareLettucePoolingClientConfiguration(redis);
        RedisSentinelConfiguration configuration =RedisConfigurationFactory.prepareSentinelConfig(redis);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, lettucePoolingClientConfiguration);
        return factory;

    }

    /**
     * Create redis cluster configuration factory redis connection factory.
     *
     * @param redis the redis
     * @return the redis connection factory
     */
    public static RedisConnectionFactory createRedisClusterConfigurationFactory(final RedisProperties redis) {
        final LettucePoolingClientConfiguration lettucePoolingClientConfiguration = prepareLettucePoolingClientConfiguration(redis);
        RedisClusterConfiguration configuration=RedisConfigurationFactory.prepareClusterConfig(redis);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, lettucePoolingClientConfiguration);
        return factory;
    }

    /**
     * Prepare lettuce pooling client configuration lettuce pooling client configuration.
     *
     * @param redis the redis
     * @return the lettuce pooling client configuration
     */
    public static LettucePoolingClientConfiguration prepareLettucePoolingClientConfiguration(final RedisProperties redis){
        GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
        poolConfig=RedisConfigurationFactory.preparePoolConfig(redis,poolConfig);
        return LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .commandTimeout(Duration.ofMillis(redis.getTimeout()))
                .shutdownTimeout(Duration.ofMillis(redis.getTimeout()))
                .build();
    }


}
