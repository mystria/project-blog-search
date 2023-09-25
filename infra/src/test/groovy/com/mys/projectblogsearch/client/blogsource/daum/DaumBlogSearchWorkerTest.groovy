package com.mys.projectblogsearch.client.blogsource.daum

import com.mys.projectblogsearch.client.blogsource.daum.model.BlogSearchResponse
import com.mys.projectblogsearch.request.PortBlogListRequest
import com.mys.projectblogsearch.type.VendorType
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class DaumBlogSearchWorkerTest extends Specification {

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

}
