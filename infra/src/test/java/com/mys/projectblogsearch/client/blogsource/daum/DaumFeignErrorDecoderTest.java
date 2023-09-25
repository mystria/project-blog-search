package com.mys.projectblogsearch.client.blogsource.daum;

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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Tag("integration-test")
@WebMvcTest(properties = { "client.kakao.url=localhost:8081" })
@ExtendWith(MockServerExtension.class)
@SpringJUnitConfig(classes = {DaumFeignClientConfiguration.class})
@MockServerSettings(ports = {8081})
class DaumFeignErrorDecoderTest {

    @Autowired
    private DaumFeignClient client;

    private final ClientAndServer mockServer;

    public DaumFeignErrorDecoderTest(ClientAndServer mockServer) {

        this.mockServer = mockServer;
    }

    @Test
    void error_400() {

        //given
        var errorMessage = """
            {
                "errorType": "MissingParameter",
                "message": "query parameter required"
            }
            """;
        mockServer
            .when(
                request()
                    .withMethod("GET")
                    .withPath("/v2/search/blog")
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
              "meta": {
                "total_count": 5,
                "pageable_count": 5,
                "is_end": true
              },
              "documents": [
                {
                "title": "작은 <b>집</b> <b>짓기</b> 기본컨셉 - <b>집</b><b>짓기</b> 초기구상하기",
                "contents": "이 점은 <b>집</b>을 지으면서 고민해보아야 한다. 하지만, 금액에 대한 가성비 대비 크게 문제되지 않을 부분이라 생각하여 설계로 극복하자고 생각했다. 전체 <b>집</b><b>짓기</b>의 기본방향은 크게 세 가지이다. 우선은 여가의 영역 증대이다. 현대 시대 일도 중요하지만, 여가시간 <b>집</b>에서 어떻게 보내느냐가 중요하니깐 이를 기본적...",
                "url": "https://brunch.co.kr/@tourism/91",
                "blogname": "정란수의 브런치",
                "thumbnail": "http://search3.kakaocdn.net/argon/130x130_85_c/7r6ygzbvBDc",
                "datetime": "2017-05-07T18:50:07.000+09:00"
                }
              ]
            }
            """;
        mockServer
            .when(
                request()
                    .withMethod("GET")
                    .withPath("/v2/search/blog")
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
