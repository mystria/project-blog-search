package com.mys.projectblogsearch.client.blogsource.daum;

import com.mys.projectblogsearch.client.blogsource.daum.model.BlogSearchRequest;
import com.mys.projectblogsearch.client.blogsource.daum.model.BlogSearchResponse;
import com.mys.projectblogsearch.client.blogsource.daum.model.Document;
import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import com.mys.projectblogsearch.response.PortBlogListResponse.BlogSummary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DaumBlogMapper {

    int DAUM_DEFAULT_OFFSET = 1;

    int DAUM_DEFAULT_LIMIT = 10;

    DaumBlogMapper INSTANCE = Mappers.getMapper(DaumBlogMapper.class);

    BlogSearchRequest toBlogSearchRequest(PortBlogListRequest request);

    @Mapping(target = "offset", source = "request.page", defaultExpression = "java(DAUM_DEFAULT_OFFSET)")
    @Mapping(target = "limit", source = "request.size", defaultExpression = "java(DAUM_DEFAULT_LIMIT)")
    @Mapping(target = "totalCount", source = "response.meta.pageableCount")
    @Mapping(target = "blogs", source = "response.documents")
    PortBlogListResponse toPortBlogListResponse(BlogSearchRequest request, BlogSearchResponse response);

    BlogSummary toBlogSummary(Document document);

}
