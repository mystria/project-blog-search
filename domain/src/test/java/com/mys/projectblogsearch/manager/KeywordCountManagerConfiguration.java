package com.mys.projectblogsearch.manager;

import com.mys.projectblogsearch.BlogSearchPort;
import com.mys.projectblogsearch.KeywordCountPort;
import com.mys.projectblogsearch.request.PortKeywordListRequest;
import com.mys.projectblogsearch.response.PortKeywordListResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@EnableCaching
public class KeywordCountManagerConfiguration {

    @Bean
    public KeywordCountPort keywordCountPort() {

        return new KeywordCountPort() {

            @Override
            public void saveKeyword(@NotNull String keyword, @NotNull Integer count) {

            }

            @Override
            public void saveKeywords(@NotNull PortKeywordListRequest request) {

            }

            @Override
            public PortKeywordListResponse getTopKeywords(@NotNull Integer limit) {
                return null;
            }
        };

    }

    @Bean
    public BlogSearchPort blogSearchPort() {

        return searchRequest -> null;

    }

}
