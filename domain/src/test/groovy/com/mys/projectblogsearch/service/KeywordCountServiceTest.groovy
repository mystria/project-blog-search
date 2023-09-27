package com.mys.projectblogsearch.service

import com.mys.projectblogsearch.KeywordCountPort
import com.mys.projectblogsearch.manager.KeywordCountReadManager
import com.mys.projectblogsearch.response.UseCaseKeywordListResponse
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class KeywordCountServiceTest extends Specification {

    @Subject
    KeywordCountService service

    KeywordCountReadManager keywordCountReadManager
    KeywordCountPort keywordCountPort

    def setup() {

        keywordCountReadManager = Mock()
        keywordCountPort = Mock()
        service = new KeywordCountService(keywordCountReadManager, keywordCountPort)

    }

    def 'getPopularTop10KeywordList()'() {

        given:
        def res = Instancio.of(UseCaseKeywordListResponse.class)
            .create()

        when:
        service.getPopularTop10KeywordList()

        then:
        1 * keywordCountReadManager.getPopularKeywordList(_ as KeywordCountPort, _ as Integer) >> res

    }

}
