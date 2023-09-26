package com.mys.projectblogsearch.manager

import com.mys.projectblogsearch.response.PortKeywordListResponse
import java.util.stream.IntStream
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class KeywordCountMapperTest extends Specification {

    @Subject
    KeywordCountMapper mapper

    def setup() {

        mapper = KeywordCountMapper.INSTANCE

    }

    def 'toUseCaseBlogListResponse()'() {

        given:
        def res = Instancio.of(PortKeywordListResponse.class)
            .create()

        when:
        def useCaseRes = mapper.toUseCaseBlogListResponse(res)

        then:
        res.keywordCounts.size() == useCaseRes.keywordCounts.size()
        res.keywordCounts*.keyword == useCaseRes.keywordCounts*.keyword
        res.keywordCounts*.count == useCaseRes.keywordCounts*.count
        def size = res.keywordCounts.size()
        IntStream.range(0, size).forEach { it + 1 == useCaseRes.keywordCounts[it].rank }

    }

    def 'toKeywordCount()'() {

        given:
        def res = Instancio.of(PortKeywordListResponse.KeywordCount.class)
            .create()

        when:
        def useCaseRes = mapper.toKeywordCount(res, rank_)

        then:
        res.keyword == useCaseRes.keyword
        res.count == useCaseRes.count
        useCaseRank_ == useCaseRes.rank

        where:
        rank_ | useCaseRank_
        1     | 1
        2     | 2
        3     | 3
    }

}
