package org.paranora.cache.configuration.redis;

import lombok.Data;
import org.redisson.api.NatMapper;
import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(value = "redis", ignoreUnknownFields = false)
public class RedisProperties {

    private int database = 0;
    private String host = "localhost";
    private String password;
    private String clientName;
    private int port = 6379;
    private int timeout = 5000;
    private RedisProperties.PoolConfiguration pool;
    private RedisProperties.SentinelConfiguration sentinel;
    private RedisProperties.ClusterConfiguration cluster;
    private Map<String, String> keyPrefixs;
    private boolean usePool = true;
    private boolean useSsl = false;
    private boolean enabled = false;
    private String product;

    private int idleConnectionTimeout = 10000;
    private int connectTimeout = 10000;
    private int retryAttempts = 3;
    private int retryInterval = 1500;
    private int pingConnectionInterval = 1000;

    public enum Product {
        Jedis("1", "Jedis", "Jedis", ""),
        Redisson("2", "Redisson", "Redisson", ""),
        Lettuce("3", "Lettuce", "Lettuce", ""),
        NULL(null, null, null, null);

        private String no;
        private String code;
        private String name;
        private String description;

        private Product(String no, String code, String name, String description) {
            this.no = no;
            this.code = code;
            this.description = description;
            this.name = name;
        }

        public String no() {
            return no;
        }

        public String code() {
            return code;
        }

        public String description() {
            return this.description;
        }


        @Override
        public String toString() {
            return this.name;
        }
    }

    public enum ClusterType {
        Sentinel("1", "Sentinel", "Sentinel", ""),
        MasterSlave("2", "PROXY", "MasterSlave", ""),
        Single("3", "Single", "Single", ""),
        Replicated("4", "Replicated", "Replicated", ""),
        Cluster("5", "Cluster", "Cluster", ""),
        NULL(null, null, null, null);

        private String no;
        private String code;
        private String name;
        private String description;

        private ClusterType(String no, String code, String name, String description) {
            this.no = no;
            this.code = code;
            this.description = description;
            this.name = name;
        }

        public String no() {
            return no;
        }

        public String code() {
            return code;
        }

        public String description() {
            return this.description;
        }


        @Override
        public String toString() {
            return this.name;
        }
    }

    @Data
    public static class ClusterConfiguration implements Serializable {
        private String type = ClusterType.Cluster.code();
        private LoadBalancer loadBalancer = new RoundRobinLoadBalancer();
        private int subscriptionsPerConnection = 5;
        private int slaveConnectionMinimumIdleSize = 32;
        private int slaveConnectionPoolSize = 64;
        private int failedSlaveReconnectionInterval = 3000;
        private int failedSlaveCheckInterval = 180000;
        private int masterConnectionMinimumIdleSize = 32;
        private int masterConnectionPoolSize = 64;
        private int subscriptionConnectionMinimumIdleSize = 1;
        private int subscriptionConnectionPoolSize = 50;
        private int scanInterval = 1000;

        private boolean enabled = false;
        private int maxRedirects = -1;
        private List<String> nodes;
    }

    @Data
    public static class PoolConfiguration implements Serializable {

        private int numTestsPerEvictionRun = 0;
        private long softMinEvictableIdleTimeMillis = 0;
        private long minEvictableIdleTimeMillis = 0;
        private boolean lifo = true;
        private boolean fairness;
        private boolean testOnCreate = false;
        private boolean testOnBorrow = false;
        private boolean testOnReturn = false;
        private boolean testWhileIdle = false;
        private int maxIdle = 8;
        private int minIdle;
        private int maxActive = 8;
        private int maxWait = -1;
    }

    @Data
    public static class SentinelConfiguration implements Serializable {
        private boolean enabled = false;
        private String master;
        private int database = 0;
        private int scanInterval = 1000;
        private boolean checkSentinelsList = true;
        private NatMapper natMapper = NatMapper.direct();
//        private List<String> nodes;
    }
}
