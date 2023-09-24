package com.mys.projectblogsearch;

import com.mys.projectblogsearch.response.UseCaseKeywordListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KeywordCountService implements KeywordCountUseCase {

    @Override
    public UseCaseKeywordListResponse getPopularTop10KeywordList() {

        return null;
    }

}
