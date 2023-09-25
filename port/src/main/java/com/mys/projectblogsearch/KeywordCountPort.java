package com.mys.projectblogsearch;

import com.mys.projectblogsearch.request.PortKeywordListRequest;
import com.mys.projectblogsearch.response.PortKeywordListResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface KeywordCountPort {

    void saveKeywords(@NotNull PortKeywordListRequest request);

    PortKeywordListResponse getTopKeywords(@NotNull Integer limit);

}
