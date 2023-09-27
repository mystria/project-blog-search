package com.mys.projectblogsearch.client.blogsource.naver;

import com.mys.projectblogsearch.client.BlogSearchWorker;
import com.mys.projectblogsearch.client.blogsource.QueryValidator;
import com.mys.projectblogsearch.client.blogsource.naver.model.BlogSearchRequest;
import com.mys.projectblogsearch.client.blogsource.naver.model.SortType;
import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import com.mys.projectblogsearch.type.VendorType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverBlogSearchWorker implements BlogSearchWorker {

    private static final NaverBlogMapper MAPPER = NaverBlogMapper.INSTANCE;

    private final NaverFeignClient feignClient;

    @Override
    public VendorType getVendorType() {

        return VendorType.NAVER;

    }

    @Override
    public PortBlogListResponse search(@NotNull @Valid PortBlogListRequest request) {

        QueryValidator.validate(request.getQuery());
        BlogSearchRequest blogSearchRequest = MAPPER.toBlogSearchRequest(request);
        return MAPPER.toPortBlogListResponse(blogSearchRequest,
            feignClient.search(
                blogSearchRequest.getQuery(),
                Optional.ofNullable(blogSearchRequest.getSort())
                    .map(SortType::getValue)
                    .orElse(null),
                blogSearchRequest.getStart(),
                blogSearchRequest.getDisplay()));

    }

}
