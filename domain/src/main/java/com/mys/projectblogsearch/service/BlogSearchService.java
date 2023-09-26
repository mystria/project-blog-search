package com.mys.projectblogsearch.service;

import com.mys.projectblogsearch.BlogSearchUseCase;
import com.mys.projectblogsearch.manager.BlogSearchManager;
import com.mys.projectblogsearch.manager.KeywordCountWriteManager;
import com.mys.projectblogsearch.request.UseCaseBlogListRequest;
import com.mys.projectblogsearch.response.UseCaseBlogListResponse;
import com.mys.projectblogsearch.util.KeywordSeparatorUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchService implements BlogSearchUseCase {

    private final BlogSearchManager blogSearchManager;

    private final KeywordCountWriteManager keywordCountManager;

    @Override
    public UseCaseBlogListResponse search(@NotNull @Valid UseCaseBlogListRequest request) {

        log.debug("Search for {}", request.getQuery());
        KeywordSeparatorUtil.separateToStream(request.getQuery())
            .map(keywordCountManager::get)
            .map(keywordCountManager::hit)
            .forEach(hitCount -> log.debug("Keyword hits: {}", hitCount));

        return blogSearchManager.search(request);

    }

}
