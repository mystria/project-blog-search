package com.mys.projectblogsearch.client.blogsource.daum.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogSearchRequest {

    @NotBlank
    private String query;

    private SortType sort;

    private Integer page;

    private Integer size;

}
