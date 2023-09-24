package com.mys.projectblogsearch.v1.controller;

import com.mys.projectblogsearch.v1.response.StandardFailureResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = StandardFailureResponse.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found", content = {@Content(schema = @Schema(implementation = StandardFailureResponse.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(implementation = StandardFailureResponse.class))}),
    @ApiResponse(responseCode = "501", description = "Not Implemented", content = {@Content(schema = @Schema(implementation = StandardFailureResponse.class))})
})
@RestController
@RequestMapping(value = "/v1")
public abstract class StandardController {

}
