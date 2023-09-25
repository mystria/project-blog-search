package com.mys.projectblogsearch.client.blogsource.daum;

import feign.Response;
import feign.codec.ErrorDecoder;

public class DaumFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        return new RuntimeException("Client Error: " + response.reason());

    }

}
