package com.mys.projectblogsearch.manager

import com.mys.projectblogsearch.BlogSearchPort
import com.mys.projectblogsearch.request.PortBlogListRequest
import com.mys.projectblogsearch.request.UseCaseBlogListRequest
import com.mys.projectblogsearch.response.PortBlogListResponse
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class BlogSearchManagerTest extends Specification {

    @Subject
    BlogSearchManager manager

    BlogSearchPort port

    def setup() {

        port = Mock()
        manager = new BlogSearchManager()

    }

    def 'search()'() {

        given:
        def dto = Instancio.of(UseCaseBlogListRequest.class)
            .create()
        def portRes = Instancio.of(PortBlogListResponse.class)
            .create()

        when:
        def res = manager.search(port, dto)

        then:
        null != res
        1 * port.search(_ as PortBlogListRequest) >> portRes

    }

}
