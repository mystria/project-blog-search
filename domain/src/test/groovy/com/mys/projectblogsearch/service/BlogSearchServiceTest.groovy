package com.mys.projectblogsearch.service

import com.mys.projectblogsearch.manager.BlogSearchManager
import com.mys.projectblogsearch.manager.KeywordCountManager
import com.mys.projectblogsearch.model.HitCount
import com.mys.projectblogsearch.request.UseCaseBlogListRequest
import com.mys.projectblogsearch.response.UseCaseBlogListResponse
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class BlogSearchServiceTest extends Specification {

    @Subject
    BlogSearchService service

    BlogSearchManager blogSearchManager
    KeywordCountManager keywordCountManager

    def setup() {

        blogSearchManager = Mock()
        keywordCountManager = Mock()
        service = new BlogSearchService(blogSearchManager, keywordCountManager)

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
        1 * blogSearchManager.search(_ as UseCaseBlogListRequest) >> res

    }

}
