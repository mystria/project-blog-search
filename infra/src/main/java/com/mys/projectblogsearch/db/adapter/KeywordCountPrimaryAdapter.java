package com.mys.projectblogsearch.db.adapter;

import com.mys.projectblogsearch.KeywordCountPort;
import com.mys.projectblogsearch.db.dao.KeywordCountDao;
import com.mys.projectblogsearch.response.PortKeywordListResponse;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class KeywordCountPrimaryAdapter implements KeywordCountPort {

    private final KeywordCountDao keywordCountDao;

    @Override
    public void saveKeywords(@NotNull Map<String, Integer> keywordCountMap) {

        // TODO
    }

    @Override
    public PortKeywordListResponse getTop10Keywords() {
        return null;
    }

}

