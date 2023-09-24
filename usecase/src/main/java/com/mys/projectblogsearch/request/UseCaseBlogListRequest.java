package com.mys.projectblogsearch.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UseCaseBlogListRequest {

    private String query;

    private String sort;

    private Integer page;

    private Integer size;

}
