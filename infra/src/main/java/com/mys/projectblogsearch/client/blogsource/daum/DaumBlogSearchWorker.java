package com.mys.projectblogsearch.client.blogsource.daum;

import com.mys.projectblogsearch.client.BlogSearchWorker;
import com.mys.projectblogsearch.client.blogsource.daum.model.BlogSearchRequest;
import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import com.mys.projectblogsearch.type.VendorType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DaumBlogSearchWorker implements BlogSearchWorker {

    private static final DaumBlogMapper MAPPER = DaumBlogMapper.INSTANCE;

    private final DaumFeignClient feignClient;

    @Override
    public VendorType getVendorType() {

        return VendorType.DAUM;

    }

    @Override
    public PortBlogListResponse search(@NotNull @Valid PortBlogListRequest request) {

        BlogSearchRequest blogSearchRequest = MAPPER.toBlogSearchRequest(request);
        return MAPPER.toPortBlogListResponse(blogSearchRequest,
            feignClient.search(
                blogSearchRequest.getQuery(),
                blogSearchRequest.getSort().getValue(),
                blogSearchRequest.getPage(),
                blogSearchRequest.getSize()));

    }

}
