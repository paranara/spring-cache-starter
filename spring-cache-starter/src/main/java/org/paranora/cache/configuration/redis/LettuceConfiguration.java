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
@Profile({"lettuce"})
@Configuration
public class LettuceConfiguration {

    private final Logger logger = LoggerFactory.getLogger(LettuceConfiguration.class);

    @Autowired
    private RedisProperties properties;

    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public RedisConnectionFactory lettuceConnectionFactory() {
        logger.info(String.format("begin create bean lettuceConnectionFactory ! by paranora."));
        return LettuceConfigurationFactory.createRedisClusterConfigurationFactory(properties);
    }

}
