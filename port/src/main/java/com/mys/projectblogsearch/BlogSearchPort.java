package com.mys.projectblogsearch;

import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BlogSearchPort {

    PortBlogListResponse search(@Valid PortBlogListRequest searchRequest);

}
