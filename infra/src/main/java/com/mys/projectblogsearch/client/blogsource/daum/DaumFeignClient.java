package com.mys.projectblogsearch.client.blogsource.daum;

import com.mys.projectblogsearch.client.blogsource.daum.model.BlogSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "daum",
    configuration = {DaumFeignErrorDecoder.class},
    url = "${client.kakao.url}")
public interface DaumFeignClient {

    @GetMapping(value = "/v2/search/blog",
        consumes = "application/json", produces = "application/json")
    BlogSearchResponse search(@RequestParam String query, @RequestParam String sort,
        @RequestParam Integer page, @RequestParam Integer size);

}
