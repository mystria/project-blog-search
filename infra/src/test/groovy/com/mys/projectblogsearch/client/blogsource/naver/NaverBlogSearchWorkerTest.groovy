package com.mys.projectblogsearch.client.blogsource.naver

import com.mys.projectblogsearch.client.blogsource.daum.DaumBlogSearchWorker
import com.mys.projectblogsearch.client.blogsource.daum.DaumFeignClient
import com.mys.projectblogsearch.client.blogsource.daum.model.BlogSearchResponse
import com.mys.projectblogsearch.request.PortBlogListRequest
import com.mys.projectblogsearch.type.SortType
import com.mys.projectblogsearch.type.VendorType
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class NaverBlogSearchWorkerTest extends Specification {

    @Subject
    DaumBlogSearchWorker worker

    DaumFeignClient feignClient

    def setup() {

        feignClient = Mock()
        worker = new DaumBlogSearchWorker(feignClient)

    }

    def 'getVendorType()'() {

        when:
        def vendorType = worker.getVendorType()

        then:
        VendorType.DAUM == vendorType

    }

    def 'search()'() {

        given:
        def req = Instancio.of(PortBlogListRequest.class)
            .create()
        def vendorDto = Instancio.of(BlogSearchResponse.class)
            .create()

        when:
        def dto = worker.search(req)

        then:
        null != dto
        1 * feignClient.search(_ as String, _ as String, _ as Integer, _ as Integer) >> vendorDto

    }

    def 'searchWithTooLongWords()'() {

        given:
        def req = PortBlogListRequest.builder()
            .vendorType(VendorType.DAUM)
            .query(query_)
            .build()
        def vendorDto = Instancio.of(BlogSearchResponse.class)
            .create()

        when:
        worker.search(req)

        then:
        0 * feignClient.search(_ as String, _ as String, _ as Integer, _ as Integer) >> vendorDto
        def e = thrown(IllegalArgumentException)
        e.message.startsWith("Too long words: ")

        where:
        query_ << ["1" * 256, "가" * 256, "A" * 256, "ABC123###ㅋ" * 26]

    }

    def 'searchWithNotTooLongWords()'() {

        given:
        def req = PortBlogListRequest.builder()
            .vendorType(VendorType.DAUM)
            .query(query_)
            .sort(SortType.ACCURACY)
            .page(1)
            .size(10)
            .build()
        def vendorDto = Instancio.of(BlogSearchResponse.class)
            .create()

        when:
        worker.search(req)

        then:
        1 * feignClient.search(_ as String, _ as String, _ as Integer, _ as Integer) >> vendorDto
        noExceptionThrown()

        where:
        query_ << ["1" * 255, "가" * 255, "A" * 255, "A" * 200 + " " + "A" * 200, "A" * 200 + "   " + "A" * 200]

    }

}
