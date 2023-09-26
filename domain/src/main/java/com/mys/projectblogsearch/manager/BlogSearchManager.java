package com.mys.projectblogsearch.manager;

import com.mys.projectblogsearch.BlogSearchPort;
import com.mys.projectblogsearch.request.UseCaseBlogListRequest;
import com.mys.projectblogsearch.response.UseCaseBlogListResponse;
import com.mys.projectblogsearch.type.VendorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlogSearchManager {

    private static final BlogSearchMapper MAPPER = BlogSearchMapper.INSTANCE;

    private final BlogSearchPort blogSearchPort;

    public UseCaseBlogListResponse search(UseCaseBlogListRequest request) {

        return MAPPER.toUseCaseBlogListResponse(
            blogSearchPort.search(
                MAPPER.toPortBlogListRequest(request, VendorType.DAUM)));

    }

}
