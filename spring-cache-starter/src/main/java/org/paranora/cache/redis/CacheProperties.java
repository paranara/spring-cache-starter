package org.paranora.cache.redis;

import lombok.Data;
import org.paranora.cache.redis.serializer.RedisKeySerializer;
import org.paranora.cache.redis.serializer.RedisValueSerializer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;

/**
 * The type Cache properties.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "cache")
public class CacheProperties implements BeanFactoryAware {

    private BeanFactory beanFactory;

    private Integer exprieTime;
    private String valueSerializerClass;
    private String keySerializerClass;
    private LinkedHashMap<String, Integer> exprieTimes;

    private RedisKeySerializer keySerializer;
    private RedisValueSerializer valueSerializer;

    private String keyPrefix;
    private String keyDelimiter;

    private CustomCacheKeyPrefix cacheKeyPrefix;

    /**
     * Instantiates a new Cache properties.
     */
    public CacheProperties() {
        defaultProperties();
    }

    /**
     * Default properties.
     */
    public void defaultProperties() {
        this.exprieTime = 86400;
        this.keyPrefix = "";
        this.keyDelimiter = ":";
        this.valueSerializerClass = "org.paranora.cache.redis.serializer.RedisValueJdkSerializer";
        this.keySerializerClass = "org.paranora.cache.redis.serializer.RedisKeyJdkSerializer";
    }

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        createCacheKeyPrefixBean();
        createRedisValueSerializerBean();
        createRedisKeySerializerBean();
    }

    /**
     * Create cache key prefix bean.
     */
    protected void createCacheKeyPrefixBean() {
        if (!ObjectUtils.isEmpty(this.cacheKeyPrefix)) return;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CustomCacheKeyPrefix.class);
        builder.addPropertyValue("prefix", this.keyPrefix);
        builder.addPropertyValue("delimiter", this.keyDelimiter);
        builder.setPrimary(true);
        getBeanFactory().registerBeanDefinition(CustomCacheKeyPrefix.class.getSimpleName(), builder.getBeanDefinition());
        setCacheKeyPrefix(this.beanFactory.getBean(CustomCacheKeyPrefix.class));
    }

    /**
     * Create redis value serializer bean.
     */
    protected void createRedisValueSerializerBean() {
        if (!ObjectUtils.isEmpty(this.valueSerializer) || ObjectUtils.isEmpty(this.valueSerializerClass)) return;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(this.valueSerializerClass);
        builder.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_NO);
        getBeanFactory().registerBeanDefinition(RedisValueSerializer.class.getSimpleName(), builder.getBeanDefinition());
        setValueSerializer(this.beanFactory.getBean(RedisValueSerializer.class));
    }

    /**
     * Create redis key serializer bean.
     */
    protected void createRedisKeySerializerBean() {
        if (!ObjectUtils.isEmpty(this.keySerializer) || ObjectUtils.isEmpty(this.keySerializerClass)) return;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(this.keySerializerClass);
        builder.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_NO);
        getBeanFactory().registerBeanDefinition(RedisKeySerializer.class.getSimpleName(), builder.getBeanDefinition());
        setKeySerializer(this.beanFactory.getBean(RedisKeySerializer.class));
    }

    /**
     * Gets bean factory.
     *
     * @return the bean factory
     */
    protected DefaultListableBeanFactory getBeanFactory() {
        return (DefaultListableBeanFactory) this.beanFactory;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}

