package org.paranora.cache.configuration.ehcache;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.core.io.ClassPathResource;

public class EhCacheConfigurationFactory {

    public static EhCacheCacheManager createEhCacheCacheManager(String configLocation) {
        EhCacheManagerFactoryBean factoryBean=new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource(configLocation));
        EhCacheCacheManager cacheManager =new EhCacheCacheManager();
        cacheManager.setCacheManager(factoryBean.getObject());
        return cacheManager;
    }
}
