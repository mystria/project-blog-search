package com.mys.projectblogsearch.manager

import com.mys.projectblogsearch.KeywordCountPort
import com.mys.projectblogsearch.response.PortKeywordListResponse
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class KeywordCountReadManagerTest extends Specification {

    @Subject
    KeywordCountReadManager manager

    KeywordCountPort port

    def setup() {

        port = Mock()
        manager = new KeywordCountReadManager()

    }

    def 'getPopularKeywordList()'() {

        given:
        def limit = 10
        def portRes = Instancio.of(PortKeywordListResponse.class)
            .create()

        when:
        def res = manager.getPopularKeywordList(port, limit)

        then:
        null != res
        1 * port.getTopKeywords(_ as Integer) >> portRes

    }

}
