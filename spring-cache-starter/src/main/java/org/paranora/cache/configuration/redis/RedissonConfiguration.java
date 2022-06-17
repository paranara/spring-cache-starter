package org.paranora.cache.configuration.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@AutoConfigureOrder(0)
@Profile("redisson")
@Configuration
public class RedissonConfiguration {

    private final Logger logger = LoggerFactory.getLogger(RedissonConfiguration.class);

    @Autowired
    private RedisProperties properties;

    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public RedisConnectionFactory redissonConnectionFactory(){
        logger.info(String.format("begin create bean redissonConnectionFactory ! by paranora."));
        return RedissonConfigurationFactory.createRedisConfigurationFactory(properties);
    }

}


