package com.mys.projectblogsearch.client.blogsource.daum;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaumFeignClientConfiguration {

    private static final String KAKAO_API_KEY_FORMAT = "KakaoAK %s";

    @Value("${client.kakao.api-key}")
    private String authToken;

    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> requestTemplate.header("Authorization", String.format(KAKAO_API_KEY_FORMAT, authToken));

    }

}
