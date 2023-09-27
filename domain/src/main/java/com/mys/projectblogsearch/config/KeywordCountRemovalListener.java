package com.mys.projectblogsearch.config;

import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.mys.projectblogsearch.KeywordCountPort;
import com.mys.projectblogsearch.model.HitCount;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component("removalListener")
@RequiredArgsConstructor
public class KeywordCountRemovalListener implements RemovalListener<Object, Object> {

    private final KeywordCountPort keywordCountPort;

    @Async("keywordCountExecutor")
    @Override
    public void onRemoval(@Nullable Object key, @Nullable Object value, RemovalCause cause) {

        if (value instanceof HitCount hitCount) {
            log.info("Key {}({}) was stacked in DB ({})", hitCount.getKeyword(), hitCount.getCount(), cause);
            if (StringUtils.isBlank(hitCount.getKeyword()) || hitCount.getCount() < 1)
                return;
            keywordCountPort.saveKeyword(hitCount.getKeyword(), hitCount.getCount());
        }

    }

}
