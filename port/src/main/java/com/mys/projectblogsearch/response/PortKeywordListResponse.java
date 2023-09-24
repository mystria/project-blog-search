package com.mys.projectblogsearch.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PortKeywordListResponse {

    private List<KeywordCount> keywordCountList;

    @Getter
    @Builder
    public static class KeywordCount {

        private String value;

        private String count;

    }

}
