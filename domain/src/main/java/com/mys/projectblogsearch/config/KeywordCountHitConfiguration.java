package com.mys.projectblogsearch.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.RemovalListener;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class KeywordCountHitConfiguration {

    public static final String CACHE_NAME = "hit";

    public static final long SCHEDULING_EXPIRY_MILLISECONDS = 10000L;

    private static final Integer EXPIRY_SECONDS = 5;

    @Bean("hitCaffeineCache")
    public CaffeineCache hitCaffeineCache(@Qualifier("removalListener") RemovalListener<Object, Object> removalListener) {

        return new CaffeineCache(CACHE_NAME, Caffeine.newBuilder()
            .maximumSize(100)
            .removalListener(removalListener)
            .expireAfter(new Expiry<>() {
                public long expireAfterCreate(Object key, Object value, long currentTime) {

                    return TimeUnit.SECONDS.toNanos(EXPIRY_SECONDS);

                }

                public long expireAfterUpdate(Object key, Object value,
                    long currentTime, long currentDuration) {

                    return currentDuration;

                }

                public long expireAfterRead(Object key, Object value,
                    long currentTime, long currentDuration) {

                    return currentDuration;

                }
            })
            .build());

    }

}
