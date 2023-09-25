package com.mys.projectblogsearch.service;

import com.mys.projectblogsearch.BlogSearchPort;
import com.mys.projectblogsearch.BlogSearchUseCase;
import com.mys.projectblogsearch.request.UseCaseBlogListRequest;
import com.mys.projectblogsearch.response.UseCaseBlogListResponse;
import com.mys.projectblogsearch.type.VendorType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchService implements BlogSearchUseCase {

    private static final BlogSearchMapper MAPPER = BlogSearchMapper.INSTANCE;

    private final BlogSearchPort blogSearchPort;

    @Override
    public UseCaseBlogListResponse search(@NotNull @Valid UseCaseBlogListRequest request) {

        return MAPPER.toUseCaseBlogListResponse(
            blogSearchPort.search(
                MAPPER.toPortBlogListRequest(request, VendorType.DAUM)));

    }

}
