package com.mys.projectblogsearch.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Tag("integration-test")
@SpringBootTest
@SpringJUnitConfig(classes = {KeywordCountManagerConfiguration.class})
class KeywordCountWriteManagerTest {

    @Autowired
    private KeywordCountWriteManager manager;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void loadContextSuccessfully() {

        assertNotNull(manager);
        assertNotNull(cacheManager);

    }

    @Test
    void get() {

        //given
        var keyword = "아이폰";

        //when
        var response = manager.get(keyword);

        //then
        assertNotNull(response);
        assertEquals(keyword, response.getKeyword());
        assertEquals(0, response.getCount());

    }

    @Test
    void hit() {

        //given
        var keyword = "아이폰";
        var hitIt = manager.get(keyword);

        //when
        var response1 = manager.hit(hitIt);
        var response2 = manager.hit(hitIt);
        var response3 = manager.hit(hitIt);

        //then
        assertNotNull(response1);
        assertEquals(keyword, response1.getKeyword());
        // Cache 되어 있으므로 같은 객체가 반환 되어야 한다.
        assertEquals(3, response1.getCount());
        assertEquals(3, response2.getCount());
        assertEquals(3, response3.getCount());

    }

}
