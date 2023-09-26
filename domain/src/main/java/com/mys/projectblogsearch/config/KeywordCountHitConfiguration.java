package com.mys.projectblogsearch.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.RemovalCause;
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

    private static final Integer EXPIRY_SECONDS = 10;

    @Bean("hitCaffeineCache")
    public CaffeineCache hitCaffeineCache(@Qualifier("evictionListener") RemovalListener<Object, Object> evictionListener) {

        return new CaffeineCache(CACHE_NAME, Caffeine.newBuilder()
            .maximumSize(100)
            .evictionListener(evictionListener)
            .removalListener((Object key, Object value, RemovalCause cause) ->
                log.debug("Key {} was removed ({})", key, cause))
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
