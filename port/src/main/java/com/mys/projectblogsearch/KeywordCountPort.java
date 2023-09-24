package com.mys.projectblogsearch;

import com.mys.projectblogsearch.response.PortKeywordListResponse;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import org.springframework.validation.annotation.Validated;

@Validated
public interface KeywordCountPort {

    void saveKeywords(@NotNull Map<String, Integer> keywordCountMap);

    PortKeywordListResponse getTop10Keywords();

}
