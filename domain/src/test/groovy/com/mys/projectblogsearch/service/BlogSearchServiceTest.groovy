package com.mys.projectblogsearch.service

import com.mys.projectblogsearch.BlogSearchPort
import com.mys.projectblogsearch.manager.BlogSearchManager
import com.mys.projectblogsearch.manager.KeywordCountWriteManager
import com.mys.projectblogsearch.model.HitCount
import com.mys.projectblogsearch.request.UseCaseBlogListRequest
import com.mys.projectblogsearch.response.UseCaseBlogListResponse
import org.instancio.Instancio
import org.instancio.Select
import spock.lang.Specification
import spock.lang.Subject

class BlogSearchServiceTest extends Specification {

    @Subject
    BlogSearchService service

    BlogSearchManager blogSearchManager
    BlogSearchPort blogSearchPort
    KeywordCountWriteManager keywordCountManager

    def setup() {

        blogSearchManager = Mock()
        blogSearchPort = Mock()
        keywordCountManager = Mock()
        service = new BlogSearchService(blogSearchManager, blogSearchPort,
            keywordCountManager)

    }

    def 'search()'() {

        given:
        def req = Instancio.of(UseCaseBlogListRequest.class)
            .create()
        def res = Instancio.of(UseCaseBlogListResponse.class)
            .create()

        when:
        service.search(req)

        then:
        1 * keywordCountManager.get(_ as String) >> new HitCount("a")
        1 * keywordCountManager.hit(_ as HitCount) >> new HitCount("a")
        1 * blogSearchManager.search(_ as BlogSearchPort, _ as UseCaseBlogListRequest) >> res

    }

    def 'search() - filter invalid keyword'() {

        given:
        def req = Instancio.of(UseCaseBlogListRequest.class)
            .set(Select.field(UseCaseBlogListRequest.class, "query"), query_)
            .create()
        def res = Instancio.of(UseCaseBlogListResponse.class)
            .create()

        when:
        service.search(req)

        then:
        get_ * keywordCountManager.get(_ as String) >> new HitCount("a")
        hit_ * keywordCountManager.hit(_ as HitCount) >> new HitCount("a")
        1 * blogSearchManager.search(_ as BlogSearchPort, _ as UseCaseBlogListRequest) >> res

        where:
        query_    | get_ | hit_
        ""        | 0    | 0
        null      | 0    | 0
        " "       | 0    | 0
        "A"       | 1    | 1
        "A" * 254 | 1    | 1
        "A" * 255 | 1    | 1
        "나" * 255 | 1    | 1
        "A" * 256 | 0    | 0
        "나" * 256 | 0    | 0
        "A" * 999 | 0    | 0
        "A B C"   | 3    | 3

    }

}
