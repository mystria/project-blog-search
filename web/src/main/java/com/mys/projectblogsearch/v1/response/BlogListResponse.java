package com.mys.projectblogsearch.v1.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogListResponse {

    private List<BlogSummary> blogList;

    private Integer offset;

    private Integer limit;

    @Getter
    @Builder
    public static class BlogSummary {

        private String title;

        private String contents;

        private String url;

        private String blogName;

        private String thumbnail;

        private LocalDateTime dateTime;

    }

}
