package com.mys.projectblogsearch.client.blogsource.naver.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogSearchRequest {

    @NotBlank
    private String query;

    private SortType sort;

    private Integer start; // offset 과 다름, start = offset * display

    private Integer display;

}
