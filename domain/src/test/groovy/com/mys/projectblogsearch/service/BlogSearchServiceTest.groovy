package com.mys.projectblogsearch.service

import com.mys.projectblogsearch.BlogSearchPort
import com.mys.projectblogsearch.request.PortBlogListRequest
import com.mys.projectblogsearch.request.UseCaseBlogListRequest
import com.mys.projectblogsearch.response.PortBlogListResponse
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class BlogSearchServiceTest extends Specification {

    @Subject
    BlogSearchService service

    BlogSearchPort port

    def setup() {

        port = Mock()
        service = new BlogSearchService(port)

    }

    def 'toBlogSearchRequest()'() {

        given:
        def dto = Instancio.of(UseCaseBlogListRequest.class)
            .create()
        def portRes = Instancio.of(PortBlogListResponse.class)
            .create()

        when:
        def res = service.search(dto)

        then:
        null != res
        1 * port.search(_ as PortBlogListRequest) >> portRes

    }

}
