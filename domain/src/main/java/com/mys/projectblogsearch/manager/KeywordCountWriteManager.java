package com.mys.projectblogsearch.manager;

import com.mys.projectblogsearch.config.KeywordCountHitConfiguration;
import com.mys.projectblogsearch.model.HitCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeywordCountWriteManager {

    @Cacheable(KeywordCountHitConfiguration.CACHE_NAME)
    public HitCount get(String keyword) {

        return new HitCount(keyword);

    }

    @CachePut(value = KeywordCountHitConfiguration.CACHE_NAME, key = "#result.keyword")
    public HitCount hit(HitCount hitCount) {

        return hitCount.hit();

    }

    // Bypass : 검색이 오랫동안 발생하지 않는 경우를 대비해, 캐시를 주기적으로 비워 카운트를 적재한다.
    @Scheduled(fixedRate = KeywordCountHitConfiguration.SCHEDULING_EXPIRY_MILLISECONDS)
    @CacheEvict(value = KeywordCountHitConfiguration.CACHE_NAME, allEntries = true)
    public void clearCache() {

        log.info("Cache cleared.");

    }

}
