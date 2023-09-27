package com.mys.projectblogsearch.manager

import com.mys.projectblogsearch.request.UseCaseBlogListRequest
import com.mys.projectblogsearch.response.PortBlogListResponse
import com.mys.projectblogsearch.type.VendorType
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class BlogSearchMapperTest extends Specification {

    @Subject
    BlogSearchMapper mapper

    def setup() {

        mapper = BlogSearchMapper.INSTANCE

    }

    def 'toPortBlogListRequest()'() {

        given:
        def req = Instancio.of(UseCaseBlogListRequest.class)
            .create()

        when:
        def portReq = mapper.toPortBlogListRequest(req, vendor_)

        then:
        req.query == portReq.query
        req.sort == portReq.sort
        req.offset == portReq.offset
        req.limit == portReq.limit
        portVendor_ == portReq.vendorType

        where:
        vendor_          | portVendor_
        VendorType.NAVER | VendorType.NAVER
        VendorType.DAUM  | VendorType.DAUM

    }

    def 'toUseCaseBlogListResponse()'() {

        given:
        def res = Instancio.of(PortBlogListResponse.class)
            .create()

        when:
        def useCaseRes = mapper.toUseCaseBlogListResponse(res)

        then:
        res.blogs.size() == useCaseRes.blogs.size()
        res.blogs*.title == useCaseRes.blogs*.title
        res.blogs*.contents == useCaseRes.blogs*.contents
        res.blogs*.url == useCaseRes.blogs*.url
        res.blogs*.thumbnail == useCaseRes.blogs*.thumbnail
        res.blogs*.dateTime == useCaseRes.blogs*.dateTime
        res.blogs*.blogName == useCaseRes.blogs*.blogName
        res.offset == useCaseRes.offset
        res.limit == useCaseRes.limit
        res.totalCount == useCaseRes.totalCount

    }

}
