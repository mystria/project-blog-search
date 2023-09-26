package com.mys.projectblogsearch.manager;

import com.mys.projectblogsearch.config.KeywordCountHitConfiguration;
import com.mys.projectblogsearch.model.HitCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeywordCountManager {

    @Cacheable(KeywordCountHitConfiguration.CACHE_NAME)
    public HitCount get(String keyword) {

        return new HitCount(keyword);

    }

    @CachePut(value = KeywordCountHitConfiguration.CACHE_NAME, key = "#result.keyword")
    public HitCount hit(HitCount hitCount) {

        return hitCount.hit();

    }

}
