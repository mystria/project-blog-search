package com.mys.projectblogsearch.client.blogsource.naver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mys.projectblogsearch.client.blogsource.naver.model.SortType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Tag("integration-test")
@WebMvcTest
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@SpringJUnitConfig(classes = {NaverFeignClientConfiguration.class})
class NaverFeignClientTest {

    @Autowired
    private NaverFeignClient client;

    @Test
    void search() {

        //given
        var query = "아이폰";

        //when
        var response = client.search(query, null, null, null);

        //then
        assertNotNull(response);
        assertNotNull(response.getTotal());
        assertNotNull(response.getItems().get(0).getTitle());
        assertNotNull(response.getItems().get(0).getDescription());
        assertNotNull(response.getItems().get(0).getLink());
        assertNotNull(response.getItems().get(0).getBloggerName());
        assertNotNull(response.getItems().get(0).getPostDate());

    }

    @Test
    void searchWithSort() {

        //given
        var query = "아이폰";

        //when
        var response1 = client.search(query, SortType.ACCURACY.getValue(), null, null);
        var response2 = client.search(query, SortType.RECENCY.getValue(), null, null);

        //then
        assertNotNull(response1);
        assertNotNull(response2);

    }

    @Test
    void searchWithPage() {

        //given
        var query = "아이폰";

        //when
        var response1 = client.search(query, null, 1, 10);
        var response2 = client.search(query, null, 1, 5);

        //then
        assertNotNull(response1);
        assertNotNull(response2);
        assertEquals(10, response1.getItems().size());
        assertEquals(5, response2.getItems().size());

    }

}
