package com.mys.projectblogsearch.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UseCaseKeywordListResponse {

    private List<KeywordCount> keywordCounts;

    @Getter
    @Builder
    public static class KeywordCount {

        private Integer rank;

        private String keyword;

        private Integer count;
    }

}
