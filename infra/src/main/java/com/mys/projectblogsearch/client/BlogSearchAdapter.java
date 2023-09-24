package com.mys.projectblogsearch.client;

import com.mys.projectblogsearch.BlogSearchPort;
import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlogSearchAdapter implements BlogSearchPort {

    @Override
    public PortBlogListResponse search(PortBlogListRequest searchRequest) {

        return null;
    }

}
