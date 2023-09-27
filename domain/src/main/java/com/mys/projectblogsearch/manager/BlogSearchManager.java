package com.mys.projectblogsearch.manager;

import com.mys.projectblogsearch.BlogSearchPort;
import com.mys.projectblogsearch.request.UseCaseBlogListRequest;
import com.mys.projectblogsearch.response.UseCaseBlogListResponse;
import com.mys.projectblogsearch.type.VendorType;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlogSearchManager {

    private static final BlogSearchMapper MAPPER = BlogSearchMapper.INSTANCE;

    @CircuitBreaker(name = "blogs", fallbackMethod = "searchFallback")
    public UseCaseBlogListResponse search(BlogSearchPort blogSearchPort, UseCaseBlogListRequest request) {

        return MAPPER.toUseCaseBlogListResponse(
            blogSearchPort.search(
                MAPPER.toPortBlogListRequest(request, VendorType.DAUM)));

    }

    @SuppressWarnings("unused")
    private UseCaseBlogListResponse searchFallback(BlogSearchPort blogSearchPort, UseCaseBlogListRequest request,
        CallNotPermittedException exception) {

        log.error("CircuitBreaker is open. Fallback returned instead. {}", exception.getMessage());
        return MAPPER.toUseCaseBlogListResponse(
            blogSearchPort.search(
                MAPPER.toPortBlogListRequest(request, VendorType.NAVER)));

    }

}
