package com.mys.projectblogsearch.v1.controller;

import com.mys.projectblogsearch.BlogSearchUseCase;
import com.mys.projectblogsearch.v1.response.BlogListResponse;
import com.mys.projectblogsearch.v1.response.StandardSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogSearchController extends StandardController {

    private final BlogSearchUseCase blogSearchUseCase;

    @Tag(name = "Blog")
    @Operation(summary = "get blog list.", responses = {
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(contentSchema = @Schema(implementation = StandardSuccessResponse.class),
                schemaProperties = {
                    @SchemaProperty(name = "result", schema = @Schema(name = "result", implementation = BlogListResponse.class))
                }
            )
        )
    })
    @GetMapping("/blogs")
    public ResponseEntity<StandardSuccessResponse<BlogListResponse>> getBlogs() {

        return null;
    }

}
