package com.mys.projectblogsearch.request;

import com.mys.projectblogsearch.type.SortType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UseCaseBlogListRequest {

    @NotBlank
    private String query;

    private SortType sort;

    @Min(1)
    @Max(50)
    @NotNull
    private Integer offset;

    @Min(1)
    @Max(50)
    @NotNull
    private Integer limit;

}
