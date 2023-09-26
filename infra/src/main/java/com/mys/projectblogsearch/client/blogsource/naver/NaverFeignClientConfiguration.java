package com.mys.projectblogsearch.client.blogsource.naver;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NaverFeignClientConfiguration {

    public static final String FEIGN_CLIENT_NAME = "naver";

    @Value("${client.naver.client-id}")
    private String clientId;

    @Value("${client.naver.client-secret}")
    private String clientSecret;

    @Bean("naverRequestInterceptor")
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> requestTemplate
            .header("X-Naver-Client-Id", clientId)
            .header("X-Naver-Client-Secret", clientSecret);

    }

}
