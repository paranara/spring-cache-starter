package org.paranora.spring.test.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, QuartzAutoConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestMain.class})
@ActiveProfiles({"development","jedis"})
@Import({})
public class TestMain {

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testA() throws Exception {
        String key = "paranora-test";
        String cacheBucket="cache-bucket1";
        Cache cache = cacheManager.getCache(cacheBucket);
        cache.put(key, "hello, i am paranora.");

        Cache.ValueWrapper v = cache.get(key);
        System.out.println("end");
    }

}

