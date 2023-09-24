package com.mys.projectblogsearch;

import com.mys.projectblogsearch.request.UseCaseBlogListRequest;
import com.mys.projectblogsearch.response.UseCaseBlogListResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BlogSearchUseCase {

    UseCaseBlogListResponse search(@Valid UseCaseBlogListRequest searchRequest);

}
