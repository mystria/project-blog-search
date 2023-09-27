package com.mys.projectblogsearch.response;

import com.mys.projectblogsearch.type.SortType;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PortBlogListResponse {

    private List<BlogSummary> blogs;

    private SortType sort;

    private Integer offset;

    private Integer limit;

    private Integer totalCount;

    @Getter
    @Builder
    public static class BlogSummary {

        private String title;

        private String contents;

        private String url;

        private String blogName;

        private String thumbnail;

        private ZonedDateTime dateTime;
    }

}
