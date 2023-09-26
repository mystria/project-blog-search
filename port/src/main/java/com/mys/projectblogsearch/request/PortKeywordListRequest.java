package com.mys.projectblogsearch.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PortKeywordListRequest {

    private List<KeywordCount> keywordCounts;

    @Getter
    @Builder
    public static class KeywordCount {

        private String keyword;

        private Integer count;

    }

}
