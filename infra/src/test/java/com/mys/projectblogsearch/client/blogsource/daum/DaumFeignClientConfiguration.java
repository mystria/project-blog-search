package com.mys.projectblogsearch.client.blogsource.daum;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class DaumFeignClientConfiguration {

    private static final String KAKAO_API_KEY_FORMAT = "KakaoAK %s";

    @Value("${client.kakao.api-key}")
    private String authToken;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", String.format(KAKAO_API_KEY_FORMAT, authToken));
    }

}
