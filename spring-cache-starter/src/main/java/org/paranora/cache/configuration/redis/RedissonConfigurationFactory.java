package org.paranora.cache.configuration.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.stream.Collectors;

public class RedissonConfigurationFactory {

    public static RedisConnectionFactory createRedisConfigurationFactory(final RedisProperties redis) {
        if(RedisProperties.ClusterType.Cluster.code().equalsIgnoreCase(redis.getCluster().getType())){
            return createRedisClusterConfigurationFactory(redis);
        } else if(RedisProperties.ClusterType.Sentinel.code().equalsIgnoreCase(redis.getCluster().getType())){
            return createRedisSentineConfigurationFactory(redis);
        } else {
            return createRedisClusterConfigurationFactory(redis);
        }
    }

    public static RedisConnectionFactory createRedisClusterConfigurationFactory(final RedisProperties redis) {
        if (null == redis.getCluster() || !redis.getCluster().isEnabled()) {
            return null;
        }
        String[] nodes = new String[redis.getCluster().getNodes().size()];
        redis.getCluster().getNodes().stream().map(x -> "redis://" + x).collect(Collectors.toList()).toArray(nodes);
        Config config = new Config();
        config.useClusterServers()
                .setReadMode(ReadMode.SLAVE)
                .setTimeout(redis.getTimeout())
                .setConnectTimeout(redis.getConnectTimeout())
                .setIdleConnectionTimeout(redis.getIdleConnectionTimeout())
                .setPingConnectionInterval(redis.getPingConnectionInterval())
                .setRetryAttempts(redis.getRetryAttempts())
                .setRetryInterval(redis.getRetryInterval())
                .setPassword(redis.getPassword())
                .setClientName(redis.getClientName())
                .setLoadBalancer(redis.getCluster().getLoadBalancer())
                .setSubscriptionsPerConnection(redis.getCluster().getSubscriptionsPerConnection())
                .setSlaveConnectionMinimumIdleSize(redis.getCluster().getSlaveConnectionMinimumIdleSize())
                .setSlaveConnectionPoolSize(redis.getCluster().getSlaveConnectionPoolSize())
                .setMasterConnectionMinimumIdleSize(redis.getCluster().getMasterConnectionMinimumIdleSize())
                .setMasterConnectionPoolSize(redis.getCluster().getMasterConnectionPoolSize())
                .setSubscriptionConnectionMinimumIdleSize(redis.getCluster().getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(redis.getCluster().getSubscriptionConnectionPoolSize())
                .setScanInterval(redis.getCluster().getScanInterval())
                .addNodeAddress(nodes);

        RedissonClient redisson = Redisson.create(config);
        RedissonConnectionFactory factory = new RedissonConnectionFactory(redisson);
        return factory;
    }

    public static RedisConnectionFactory createRedisSentineConfigurationFactory(final RedisProperties redis) {
        if (null == redis.getSentinel() || !redis.getSentinel().isEnabled() || !redis.getCluster().isEnabled()) {
            return null;
        }
        String[] nodes = new String[redis.getCluster().getNodes().size()];
        redis.getCluster().getNodes().stream().map(x -> "redis://" + x).collect(Collectors.toList()).toArray(nodes);
        Config config = new Config();
        config.useSentinelServers()
                .setReadMode(ReadMode.SLAVE)
                .setTimeout(redis.getTimeout())
                .setConnectTimeout(redis.getConnectTimeout())
                .setIdleConnectionTimeout(redis.getIdleConnectionTimeout())
                .setPingConnectionInterval(redis.getPingConnectionInterval())
                .setRetryAttempts(redis.getRetryAttempts())
                .setRetryInterval(redis.getRetryInterval())
                .setPassword(redis.getPassword())
                .setClientName(redis.getClientName())
                .setLoadBalancer(redis.getCluster().getLoadBalancer())
                .setSubscriptionsPerConnection(redis.getCluster().getSubscriptionsPerConnection())
                .setSlaveConnectionMinimumIdleSize(redis.getCluster().getSlaveConnectionMinimumIdleSize())
                .setSlaveConnectionPoolSize(redis.getCluster().getSlaveConnectionPoolSize())
                .setMasterConnectionMinimumIdleSize(redis.getCluster().getMasterConnectionMinimumIdleSize())
                .setMasterConnectionPoolSize(redis.getCluster().getMasterConnectionPoolSize())
                .setSubscriptionConnectionMinimumIdleSize(redis.getCluster().getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(redis.getCluster().getSubscriptionConnectionPoolSize())
                .setScanInterval(redis.getCluster().getScanInterval())
                .setMasterName(redis.getSentinel().getMaster())
                .setDatabase(redis.getSentinel().getDatabase())
                .setScanInterval(redis.getSentinel().getScanInterval())
                .setCheckSentinelsList(redis.getSentinel().isCheckSentinelsList())
                .setNatMapper(redis.getSentinel().getNatMapper())
                .addSentinelAddress(nodes);

        RedissonClient redisson = Redisson.create(config);
        RedissonConnectionFactory factory = new RedissonConnectionFactory(redisson);
        return factory;
    }
}
