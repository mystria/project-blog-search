package com.mys.projectblogsearch.v1.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardSuccessResponse<T> {

    private T result;

}
