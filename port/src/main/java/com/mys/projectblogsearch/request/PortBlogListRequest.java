package com.mys.projectblogsearch.request;

import com.mys.projectblogsearch.type.SortType;
import com.mys.projectblogsearch.type.VendorType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PortBlogListRequest {

    @NotNull
    private VendorType vendorType;

    @NotBlank
    private String query;

    private SortType sort;

    @Min(1)
    private Integer page;

    @Min(1)
    private Integer size;

}
