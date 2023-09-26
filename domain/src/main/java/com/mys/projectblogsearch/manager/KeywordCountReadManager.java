package com.mys.projectblogsearch.manager;

import com.mys.projectblogsearch.KeywordCountPort;
import com.mys.projectblogsearch.config.KeywordCountRankConfiguration;
import com.mys.projectblogsearch.response.UseCaseKeywordListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeywordCountReadManager {

    private static final KeywordCountMapper MAPPER = KeywordCountMapper.INSTANCE;

    private final KeywordCountPort keywordCountPort;

    @Cacheable(KeywordCountRankConfiguration.CACHE_NAME)
    public UseCaseKeywordListResponse getPopularKeywordList(Integer limit) {

        return MAPPER.toUseCaseBlogListResponse(
            keywordCountPort.getTopKeywords(limit));

    }

}
