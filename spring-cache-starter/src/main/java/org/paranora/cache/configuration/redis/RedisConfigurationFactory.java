package org.paranora.cache.configuration.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Redis configuration factory.
 */
public class RedisConfigurationFactory {

    /**
     * Prepare pool config t.
     *
     * @param <T>        the type parameter
     * @param redis      the redis
     * @param poolConfig the pool config
     * @return the t
     */
    public static <T extends GenericObjectPoolConfig> T preparePoolConfig(final RedisProperties redis, T poolConfig) {
        final RedisProperties.PoolConfiguration pool = redis.getPool();
        poolConfig.setMaxTotal(pool.getMaxActive());
        poolConfig.setMaxIdle(pool.getMaxIdle());
        poolConfig.setMinIdle(pool.getMinIdle());
        poolConfig.setMaxWaitMillis(pool.getMaxWait());
        poolConfig.setLifo(pool.isLifo());
        poolConfig.setFairness(pool.isFairness());
        poolConfig.setTestWhileIdle(pool.isTestWhileIdle());
        poolConfig.setTestOnBorrow(pool.isTestOnBorrow());
        poolConfig.setTestOnReturn(pool.isTestOnReturn());
        poolConfig.setTestOnCreate(pool.isTestOnCreate());

        if (pool.getMinEvictableIdleTimeMillis() > 0) {
            poolConfig.setMinEvictableIdleTimeMillis(pool.getMinEvictableIdleTimeMillis());
        }
        if (pool.getNumTestsPerEvictionRun() > 0) {
            poolConfig.setNumTestsPerEvictionRun(pool.getNumTestsPerEvictionRun());
        }
        if (pool.getSoftMinEvictableIdleTimeMillis() > 0) {
            poolConfig.setSoftMinEvictableIdleTimeMillis(pool.getSoftMinEvictableIdleTimeMillis());
        }
        return poolConfig;
    }

    /**
     * Prepare cluster config redis cluster configuration.
     *
     * @param redis the redis
     * @return the redis cluster configuration
     */
    public static RedisClusterConfiguration prepareClusterConfig(final RedisProperties redis) {
        if (null == redis.getCluster() || !redis.getCluster().isEnabled() || null == redis.getCluster().getNodes()) {
            return null;
        }
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(redis.getCluster().getNodes());
        clusterConfiguration.setMaxRedirects(redis.getCluster().getMaxRedirects());
        clusterConfiguration.setClusterNodes(createRedisNodesForProperties(redis.getCluster().getNodes()));
        return clusterConfiguration;
    }

    /**
     * Prepare sentinel config redis sentinel configuration.
     *
     * @param redis the redis
     * @return the redis sentinel configuration
     */
    public static RedisSentinelConfiguration prepareSentinelConfig(final RedisProperties redis) {
        if (redis.getSentinel() == null || !redis.getSentinel().isEnabled() || null == redis.getCluster().getNodes()) {
            return null;
        }
        RedisSentinelConfiguration sentinelConfig = null;
        sentinelConfig = new RedisSentinelConfiguration().master(redis.getSentinel().getMaster());
        sentinelConfig.setSentinels(createRedisNodesForProperties(redis.getCluster().getNodes()));
        return sentinelConfig;
    }

    private static List<RedisNode> createRedisNodesForProperties(final List<String> nodes) {
        final List<RedisNode> redisNodes = new ArrayList<RedisNode>();
        if (null != nodes) {
            for (final String hostAndPort : nodes) {
                final String[] args = StringUtils.split(hostAndPort, ":");
                redisNodes.add(new RedisNode(args[0], Integer.parseInt(args[1])));
            }
        }
        return redisNodes;
    }
}
