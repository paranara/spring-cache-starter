package org.paranora.cache.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * The type Cache auto configuration.
 *
 */
@AutoConfigureOrder(0)
@Configuration
@Import({CacheConfiguration.class})
@AutoConfigureAfter({CacheConfiguration.class})
public class CacheAutoConfiguration {

//    @Bean
//    public SpringContextAware springContextUtil(){
//        return new SpringContextAware();
//    }
}
