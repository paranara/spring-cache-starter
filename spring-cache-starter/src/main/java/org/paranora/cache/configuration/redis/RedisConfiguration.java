package org.paranora.cache.configuration.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@AutoConfigureOrder(0)
@Configuration
@ConditionalOnProperty(value = "redis.enabled", havingValue = "true")
@EnableConfigurationProperties({RedisProperties.class})
@Import({JedisConfiguration.class, RedissonConfiguration.class, LettuceConfiguration.class})
@AutoConfigureAfter({JedisConfiguration.class, RedissonConfiguration.class, LettuceConfiguration.class})
public class RedisConfiguration {

    private final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);

    @Autowired
    private RedisProperties properties;

//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration() {
//        logger.info(String.format("begin create bean redisClusterConfiguration ! by paranora."));
//        Map<String, Object> props = new HashMap<>();
//        props.put("spring.redis.cluster.nodes", String.join(",",properties.getCluster().getNodes()));
//        props.put("spring.redis.cluster.timeout", properties.getTimeout());
//        props.put("spring.redis.cluster.max-redirects", properties.getCluster().getMaxRedirects());
//        MapPropertySource mapPropertySource = new MapPropertySource("RedisClusterConfiguration", props);
//        return new RedisClusterConfiguration(mapPropertySource);
//    }

}
