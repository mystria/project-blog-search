package com.mys.projectblogsearch.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class KeywordCountRankConfiguration {

    public static final String CACHE_NAME = "rank";

    private static final Integer EXPIRY_SECONDS = 3;

    @Bean("rankCaffeineCache")
    public CaffeineCache rankCaffeineCache() {

        return new CaffeineCache(CACHE_NAME, Caffeine.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(EXPIRY_SECONDS, TimeUnit.SECONDS)
            .build());

    }

}
