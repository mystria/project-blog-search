package com.mys.projectblogsearch.client.blogsource.naver;

import com.mys.projectblogsearch.client.BlogSearchWorker;
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
public class NaverBlogSearchWorker implements BlogSearchWorker {

    @Override
    public VendorType getVendorType() {

        return VendorType.NAVER;

    }

    @Override
    public PortBlogListResponse search(@NotNull @Valid PortBlogListRequest request) {

        throw new UnsupportedOperationException("NAVER not supported yet");

    }

}
