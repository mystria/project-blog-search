package com.mys.projectblogsearch.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mys.projectblogsearch.type.SortType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "블로그 리스트 응답")
public class BlogListResponse {

    @Schema(description = "블로그 리스트")
    private List<BlogSummary> blogs;

    @Schema(description = "정렬 기준")
    private SortType sort;

    @Schema(description = "페이지 시작 위치")
    private Integer offset;

    @Schema(description = "한 페이지 크기")
    private Integer limit;

    @Schema(description = "전체 아이템 수")
    private Integer totalCount;

    @Getter
    @Builder
    @Schema(description = "블로그 요약")
    public static class BlogSummary {

        @Schema(description = "제목")
        private String title;

        @Schema(description = "내용")
        private String contents;

        @Schema(description = "블로그 게시물 URL")
        private String url;

        @Schema(description = "블로그 이름")
        private String blogName;

        @Schema(description = "썸네일 URL")
        private String thumbnail;

        @Schema(description = "블로그 게시일")
        private ZonedDateTime dateTime;

    }

}
