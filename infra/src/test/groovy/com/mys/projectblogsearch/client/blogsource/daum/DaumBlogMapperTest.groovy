package com.mys.projectblogsearch.client.blogsource.daum

import com.mys.projectblogsearch.client.blogsource.daum.model.BlogSearchRequest
import com.mys.projectblogsearch.client.blogsource.daum.model.BlogSearchResponse
import com.mys.projectblogsearch.client.blogsource.daum.model.Document
import com.mys.projectblogsearch.request.PortBlogListRequest
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class DaumBlogMapperTest extends Specification {

    @Subject
    DaumBlogMapper daumBlogMapper = DaumBlogMapper.INSTANCE

    def 'toBlogSearchRequest()'() {

        given:
        def dto = Instancio.of(PortBlogListRequest.class)
            .create()

        when:
        def vendorDto = daumBlogMapper.toBlogSearchRequest(dto)

        then:
        dto.query == vendorDto.query
        dto.page == vendorDto.page
        dto.size == vendorDto.size
        dto.sort.name() == vendorDto.sort.name()

    }

    def 'toPortBlogListResponse()'() {

        given:
        def req = Instancio.of(BlogSearchRequest.class)
            .create()
        def vendorDto = Instancio.of(BlogSearchResponse.class)
            .create()

        when:
        def dto = daumBlogMapper.toPortBlogListResponse(req, vendorDto)

        then:
        req.page == dto.offset
        req.size == dto.limit
        vendorDto.meta.totalCount == dto.totalCount
        vendorDto.documents.size() == dto.blogList.size()
        vendorDto.documents*.title == dto.blogList*.title
        vendorDto.documents*.blogName == dto.blogList*.blogName
        vendorDto.documents*.contents == dto.blogList*.contents
        vendorDto.documents*.thumbnail == dto.blogList*.thumbnail
        vendorDto.documents*.url == dto.blogList*.url
        vendorDto.documents*.dateTime == dto.blogList*.dateTime

    }

    def 'toBlogSummary()'() {

        given:
        def vendorDto = Instancio.of(Document.class)
            .create()

        when:
        def dto = daumBlogMapper.toBlogSummary(vendorDto)

        then:
        vendorDto.title == dto.title
        vendorDto.blogName == dto.blogName
        vendorDto.contents == dto.contents
        vendorDto.thumbnail == dto.thumbnail
        vendorDto.url == dto.url
        vendorDto.dateTime == dto.dateTime

    }

}
