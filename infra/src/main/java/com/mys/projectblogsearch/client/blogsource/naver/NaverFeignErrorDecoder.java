package com.mys.projectblogsearch.client.blogsource.naver;

import feign.Response;
import feign.codec.ErrorDecoder;

public class NaverFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        return new RuntimeException("Client Error: " + response.reason());

    }

}
