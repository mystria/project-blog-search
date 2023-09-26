package com.mys.projectblogsearch.client.blogsource.naver;

import com.mys.projectblogsearch.client.blogsource.naver.model.BlogSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = NaverFeignClientConfiguration.FEIGN_CLIENT_NAME,
    configuration = {NaverFeignClientConfiguration.class, NaverFeignErrorDecoder.class},
    url = "${client.naver.url}")
public interface NaverFeignClient {

    @GetMapping(value = "/v1/search/blog.json",
        consumes = "application/json", produces = "application/json")
    BlogSearchResponse search(@RequestParam String query, @RequestParam String sort,
        @RequestParam Integer start, @RequestParam Integer display);

}
