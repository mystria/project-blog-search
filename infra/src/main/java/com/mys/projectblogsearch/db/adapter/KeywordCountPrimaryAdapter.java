package com.mys.projectblogsearch.db.adapter;

import com.mys.projectblogsearch.KeywordCountPort;
import com.mys.projectblogsearch.db.model.KeywordCountEntity;
import com.mys.projectblogsearch.db.repository.KeywordCountRepository;
import com.mys.projectblogsearch.db.repository.KeywordCountRepository.GroupedKeywordCount;
import com.mys.projectblogsearch.request.PortKeywordListRequest;
import com.mys.projectblogsearch.response.PortKeywordListResponse;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class KeywordCountPrimaryAdapter implements KeywordCountPort {

    private final KeywordCountRepository repository;

    private static final KeywordCountMapper MAPPER = KeywordCountMapper.INSTANCE;

    @Override
    public void saveKeywords(@NotNull PortKeywordListRequest request) {

        List<KeywordCountEntity> entities = MAPPER.toKeywordCountEntityList(request);
        repository.saveAll(entities);
    }

    @Override
    public PortKeywordListResponse getTopKeywords(@NotNull Integer limit) {

        List<GroupedKeywordCount> list = repository.findTopKeywordsWithCount(limit);
        return MAPPER.toKeywordListResponse(list);
    }

}

