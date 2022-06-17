package org.paranora.cache.configuration;

import org.paranora.cache.spring.SpringContextAware;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author :  paranora
 * @description :  TODO
 * @date :  2021/5/21 10:07
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
