package com.mys.projectblogsearch.v1.controller;

import com.mys.projectblogsearch.BlogSearchUseCase;
import com.mys.projectblogsearch.type.SortType;
import com.mys.projectblogsearch.v1.response.BlogListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogSearchController extends StandardController {

    private static final BlogSearchMapper MAPPER = BlogSearchMapper.INSTANCE;

    private final BlogSearchUseCase blogSearchUseCase;

    @Tag(name = "Blog")
    @Operation(summary = "get blog list.", responses = {
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = BlogListResponse.class)))
    }, parameters = {
        @Parameter(name = "query", description = "검색 키워드", required = true),
        @Parameter(name = "sort", description = "정렬 옵션", example = "ACCURACY"),
        @Parameter(name = "offset", description = "페이지 번호", example = "1"),
        @Parameter(name = "limit", description = "페이지 사이즈", example = "10")
    })
    @GetMapping("/blogs")
    public ResponseEntity<BlogListResponse> getBlogs(
        @RequestParam(value = "query") String query,
        @RequestParam(value = "sort", required = false) SortType sort,
        @RequestParam(value = "offset") Integer offset,
        @RequestParam(value = "limit") Integer limit
    ) {

        return success(
            MAPPER.toBlogListResponse(
                blogSearchUseCase.search(
                    MAPPER.toUseCaseBlogListRequest(query, sort, offset, limit))));

    }

}
