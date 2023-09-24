package com.mys.projectblogsearch;

import com.mys.projectblogsearch.request.UseCaseBlogListRequest;
import com.mys.projectblogsearch.response.UseCaseBlogListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogSearchService implements BlogSearchUseCase {

    @Override
    public UseCaseBlogListResponse search(UseCaseBlogListRequest searchRequest) {

        return null;
    }

}
