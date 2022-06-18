package org.paranora.cache.configuration.ehcache;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.core.io.ClassPathResource;

/**
 * The type Eh cache configuration factory.
 */
public class EhCacheConfigurationFactory {

    /**
     * Create eh cache cache manager eh cache cache manager.
     *
     * @param configLocation the config location
     * @return the eh cache cache manager
     */
    public static EhCacheCacheManager createEhCacheCacheManager(String configLocation) {
        EhCacheManagerFactoryBean factoryBean=new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource(configLocation));
        EhCacheCacheManager cacheManager =new EhCacheCacheManager();
        cacheManager.setCacheManager(factoryBean.getObject());
        return cacheManager;
    }
}
