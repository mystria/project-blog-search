package com.mys.projectblogsearch.client.blogsource.daum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mys.projectblogsearch.client.blogsource.daum.model.SortType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Tag("integration-test")
@WebMvcTest
@SpringJUnitConfig(classes = {DaumFeignClientConfiguration.class})
class DaumFeignClientTest {

    @Autowired
    private DaumFeignClient client;

    @Test
    void search() {

        //given
        var query = "아이폰";

        //when
        var response = client.search(query, null, null, null);

        //then
        assertNotNull(response);
        assertNotNull(response.getMeta());
        assertNotNull(response.getMeta().getTotalCount());
        assertNotNull(response.getMeta().getPageableCount());
        assertNotNull(response.getMeta().getIsEnd());
        assertNotNull(response.getDocuments().get(0).getTitle());
        assertNotNull(response.getDocuments().get(0).getContents());
        assertNotNull(response.getDocuments().get(0).getUrl());
        assertNotNull(response.getDocuments().get(0).getBlogName());
        assertNotNull(response.getDocuments().get(0).getThumbnail());
        assertNotNull(response.getDocuments().get(0).getDateTime());

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
        assertEquals(10, response1.getDocuments().size());
        assertEquals(5, response2.getDocuments().size());

    }

}
