package com.mys.projectblogsearch.client.blogsource.naver

import com.mys.projectblogsearch.Constants
import com.mys.projectblogsearch.client.blogsource.naver.model.BlogSearchRequest
import com.mys.projectblogsearch.client.blogsource.naver.model.BlogSearchResponse
import com.mys.projectblogsearch.client.blogsource.naver.model.Item
import com.mys.projectblogsearch.request.PortBlogListRequest
import com.mys.projectblogsearch.type.VendorType
import java.time.ZonedDateTime
import org.instancio.Instancio
import org.instancio.Select
import spock.lang.Specification
import spock.lang.Subject

class NaverBlogMapperTest extends Specification {

    @Subject
    NaverBlogMapper naverBlogMapper

    def setup() {

        naverBlogMapper = NaverBlogMapper.INSTANCE

    }

    def 'toBlogSearchRequest()'() {

        given:
        def dto = PortBlogListRequest.builder()
            .vendorType(VendorType.NAVER)
            .query("TEST")
            .offset(offset_)
            .limit(limit_)
            .build()

        when:
        def vendorDto = naverBlogMapper.toBlogSearchRequest(dto)

        then:
        dto.sort?.name() == vendorDto.sort?.name()
        dto.query == vendorDto.query
        start_ == vendorDto.start
        display_ == vendorDto.display

        where:
        offset_ | limit_ | start_ | display_
        1       | 10     | 1      | 10
        2       | 10     | 11     | 10
        3       | 10     | 21     | 10
        1       | 20     | 1      | 20
        2       | 20     | 21     | 20
        1       | 8      | 1      | 8
        2       | 8      | 9      | 8
        3       | 8      | 17     | 8
        null    | null   | null   | null
        null    | 10     | null   | 10
        2       | null   | 11     | null // Default 로 display 를 10 으로 취급

    }

    def 'toPortBlogListResponse()'() {

        given:
        def req = Instancio.of(BlogSearchRequest.class)
            .create()
        def vendorDto = BlogSearchResponse.builder()
            .start(start_)
            .display(display_)
            .total(100)
            .items(List.of())
            .build()

        when:
        def dto = naverBlogMapper.toPortBlogListResponse(req, vendorDto)

        then:
        req.sort?.name() == dto.sort?.name()
        offset_ == dto.offset
        limit_ == dto.limit
        100 == dto.totalCount
        0 == dto.blogs.size()

        where:
        start_ | display_ | offset_ | limit_
        1      | 10       | 1       | 10
        11     | 10       | 2       | 10
        21     | 10       | 3       | 10
        1      | 20       | 1       | 20
        21     | 20       | 2       | 20
        1      | 8        | 1       | 8
        9      | 8        | 2       | 8
        17     | 8        | 3       | 8

    }

    def 'toBlogSummary()'() {

        given:
        def vendorDto = Instancio.of(Item.class)
            .set(Select.field(Item, "postDate"), "20221219")
            .create()

        when:
        def dto = naverBlogMapper.toBlogSummary(vendorDto)

        then:
        vendorDto.title == dto.title
        vendorDto.bloggerName == dto.blogName
        vendorDto.description == dto.contents
        null == dto.thumbnail
        vendorDto.link == dto.url
        ZonedDateTime.of(2022, 12, 19, 0, 0, 0, 0, Constants.DEFAULT_ZONE_ID) == dto.dateTime

    }

}
