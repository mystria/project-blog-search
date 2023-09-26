package com.mys.projectblogsearch.config;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableCaching
@Configuration
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager(@Qualifier("hitCaffeineCache") CaffeineCache hitCache) {

        List<CaffeineCache> caches = List.of(hitCache);

        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(caches);

        return simpleCacheManager;

    }

}
