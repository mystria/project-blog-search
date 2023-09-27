package com.mys.projectblogsearch.service;

import com.mys.projectblogsearch.KeywordCountPort;
import com.mys.projectblogsearch.KeywordCountUseCase;
import com.mys.projectblogsearch.manager.KeywordCountReadManager;
import com.mys.projectblogsearch.response.UseCaseKeywordListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordCountService implements KeywordCountUseCase {

    private static final Integer TOP_SIZE = 10;

    private final KeywordCountReadManager keywordCountManager;

    private final KeywordCountPort keywordCountPort;

    @Override
    public UseCaseKeywordListResponse getPopularTop10KeywordList() {

        return keywordCountManager.getPopularKeywordList(keywordCountPort, TOP_SIZE);

    }

}
