package com.mys.projectblogsearch.client.blogsource.naver;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Tag("integration-test")
@WebMvcTest(properties = { "client.naver.url=localhost:8081" })
@ExtendWith(MockServerExtension.class)
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@SpringJUnitConfig(classes = {NaverFeignClientConfiguration.class})
@MockServerSettings(ports = {8081})
class NaverFeignErrorDecoderTest {

    @Autowired
    private NaverFeignClient client;

    private final ClientAndServer mockServer;

    public NaverFeignErrorDecoderTest(ClientAndServer mockServer) {

        this.mockServer = mockServer;
    }

    @Test
    void error_400() {

        //given
        var errorMessage = """
            {
                "errorMessage": "/v1/search/blog.json : Bad Request. (Check Extension or Required Parameter)",
                "errorCode": "400"
            }
            """;
        mockServer
            .when(
                request()
                    .withMethod("GET")
                    .withPath("/v1/search/blog.json")
            )
            .respond(
                response()
                    .withContentType(MediaType.APPLICATION_JSON)
                    .withStatusCode(400)
                    .withBody(errorMessage)
            );

        //when
        var ex = assertThrows(RuntimeException.class,
            () -> client.search(null, null, null, null));

        //then
        assertTrue(ex.getMessage().startsWith("Client Error: "));

    }

    @Test
    void success() {

        //given
        var successMessage = """
            {
                "lastBuildDate": "Wed, 27 Sep 2023 02:41:38 +0900",
                "total": 5091074,
                "start": 1,
                "display": 1,
                "items": [
                    {
                        "title": "노원<b>아이폰</b>수리 정품액정으로 빠르게!",
                        "link": "https://blog.naver.com/riberocjh/223218941364",
                        "description": "노원<b>아이폰</b>수리 아이픽스코리아의 도움을 받아 해결하게 되었답니다. 얼마전에 방문했던 이야기라... 지인들이 입을모아 여기가 노원에서 <b>아이폰</b> 계열 수리 서비스 매장 중에 가장 실력이 좋다고 하더라고요.... ",
                        "bloggername": "봉이의 IT이야기",
                        "bloggerlink": "blog.naver.com/riberocjh",
                        "postdate": "20230922"
                    }
                ]
            }
            """;
        mockServer
            .when(
                request()
                    .withMethod("GET")
                    .withPath("/v1/search/blog.json")
                    .withQueryStringParameter("query", "anything")
            )
            .respond(
                response()
                    .withContentType(MediaType.APPLICATION_JSON)
                    .withStatusCode(200)
                    .withBody(successMessage)
            );

        //when
        var then = client.search("anything", null, null, null);

        //then
        assertNotNull(then);
    }

}
