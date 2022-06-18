package org.paranora.cache.configuration;

import org.paranora.cache.redis.CacheManagerFactory;
import org.paranora.cache.redis.CacheProperties;
import org.paranora.cache.configuration.redis.RedisConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.net.UnknownHostException;

/**
 * The type Cache configuration.
 *
 */
@AutoConfigureOrder(0)
@EnableCaching
@Configuration
@EnableConfigurationProperties({CacheProperties.class})
@Import({RedisConfiguration.class})
@AutoConfigureAfter({RedisConfiguration.class})
public class CacheConfiguration extends CachingConfigurerSupport {

    /**
     * The Properties.
     */
    @Autowired
    protected CacheProperties properties;

    /**
     * Cache manager cache manager.
     *
     * @param redisConnectionFactory the redis connection factory
     * @return the cache manager
     * @throws Exception the exception
     */
    @Bean
    @ConditionalOnBean({RedisConnectionFactory.class,})
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) throws Exception {
        return CacheManagerFactory.customCacheManager(redisConnectionFactory, properties);
    }

    /**
     * String redis template string redis template.
     *
     * @param redisConnectionFactory the redis connection factory
     * @return the string redis template
     * @throws UnknownHostException the unknown host exception
     */
    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        return template;
    }

}
