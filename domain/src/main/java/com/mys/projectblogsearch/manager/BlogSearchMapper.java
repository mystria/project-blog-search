package com.mys.projectblogsearch.manager;

import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.request.UseCaseBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import com.mys.projectblogsearch.response.UseCaseBlogListResponse;
import com.mys.projectblogsearch.type.VendorType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSearchMapper {

    BlogSearchMapper INSTANCE = Mappers.getMapper(BlogSearchMapper.class);

    PortBlogListRequest toPortBlogListRequest(UseCaseBlogListRequest request, VendorType vendorType);

    UseCaseBlogListResponse toUseCaseBlogListResponse(PortBlogListResponse response);

}
