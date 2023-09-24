package com.mys.projectblogsearch.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UseCaseKeywordListResponse {

    private List<KeywordCount> keywordCountList;

    @Getter
    @Builder
    public static class KeywordCount {

        private Integer rank;

        private String value;

        private Integer count;
    }

}
