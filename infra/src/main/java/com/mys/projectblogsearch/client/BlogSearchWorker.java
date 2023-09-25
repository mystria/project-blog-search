package com.mys.projectblogsearch.client;

import com.mys.projectblogsearch.type.VendorType;
import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BlogSearchWorker {

    VendorType getVendorType();

    PortBlogListResponse search(@NotNull @Valid PortBlogListRequest request);

}