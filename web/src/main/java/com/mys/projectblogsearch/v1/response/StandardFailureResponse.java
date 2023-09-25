package com.mys.projectblogsearch.v1.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardFailureResponse {

    private final int code;

    private final String message;

    public static StandardFailureResponse of(int code, String message) {

        return new StandardFailureResponse(code, message);

    }

}
