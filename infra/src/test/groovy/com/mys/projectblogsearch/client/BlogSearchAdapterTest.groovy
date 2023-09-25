package com.mys.projectblogsearch.client

import com.mys.projectblogsearch.client.blogsource.daum.DaumBlogSearchWorker
import com.mys.projectblogsearch.client.blogsource.naver.NaverBlogSearchWorker
import com.mys.projectblogsearch.request.PortBlogListRequest
import com.mys.projectblogsearch.response.PortBlogListResponse
import com.mys.projectblogsearch.type.VendorType
import org.instancio.Instancio
import org.instancio.Select
import spock.lang.Specification
import spock.lang.Subject

class BlogSearchAdapterTest extends Specification {

    @Subject
    BlogSearchAdapter adapter

    DaumBlogSearchWorker daumBlogSearchWorker
    NaverBlogSearchWorker naverBlogSearchWorker

    def setup() {

        daumBlogSearchWorker = Mock()
        daumBlogSearchWorker.getVendorType() >> VendorType.DAUM
        naverBlogSearchWorker = Mock()
        naverBlogSearchWorker.getVendorType() >> VendorType.NAVER
        adapter = new BlogSearchAdapter(List.of(daumBlogSearchWorker, naverBlogSearchWorker))
        adapter.postConstruct()

    }

    def 'search()'() {

        given:
        def req = Instancio.of(PortBlogListRequest.class)
            .set(Select.field(PortBlogListRequest.class, "vendorType"), type_)
            .create()
        def res = Instancio.of(PortBlogListResponse.class)
            .create()

        when:
        def dto = adapter.search(req)

        then:
        null != dto
        visitDaum_ * daumBlogSearchWorker.search(_ as PortBlogListRequest) >> res
        visitNaver_ * naverBlogSearchWorker.search(_ as PortBlogListRequest) >> res

        where:
        type_            | visitDaum_ | visitNaver_
        VendorType.DAUM  | 1          | 0
        VendorType.NAVER | 0          | 1

    }

}
