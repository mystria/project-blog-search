package com.mys.projectblogsearch;

import com.mys.projectblogsearch.response.UseCaseKeywordListResponse;
import org.springframework.validation.annotation.Validated;

@Validated
public interface KeywordCountUseCase {

    UseCaseKeywordListResponse getPopularTop10KeywordList();

}
