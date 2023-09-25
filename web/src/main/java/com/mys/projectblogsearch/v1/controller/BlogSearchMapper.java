package com.mys.projectblogsearch.v1.controller;

import com.mys.projectblogsearch.request.UseCaseBlogListRequest;
import com.mys.projectblogsearch.response.UseCaseBlogListResponse;
import com.mys.projectblogsearch.type.SortType;
import com.mys.projectblogsearch.v1.response.BlogListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSearchMapper {

    BlogSearchMapper INSTANCE = Mappers.getMapper(BlogSearchMapper.class);

    @Mapping(target = "query", expression = "java(query.trim())")
    UseCaseBlogListRequest toUseCaseBlogListRequest(String query, SortType sort, Integer page, Integer size);

    BlogListResponse toBlogListResponse(UseCaseBlogListResponse result);

}
