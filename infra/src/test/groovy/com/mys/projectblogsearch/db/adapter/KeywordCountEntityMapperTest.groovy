package com.mys.projectblogsearch.db.adapter

import com.mys.projectblogsearch.db.repository.KeywordCountRepository
import com.mys.projectblogsearch.request.PortKeywordListRequest
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class KeywordCountEntityMapperTest extends Specification {

    @Subject
    KeywordCountEntityMapper keywordCountMapper = KeywordCountEntityMapper.INSTANCE

    def 'toKeywordCountEntityList()'() {

        given:
        def dto = Instancio.of(PortKeywordListRequest.class)
            .create()

        when:
        def entities = keywordCountMapper.toKeywordCountEntityList(dto)

        then:
        dto.keywordCounts*.keyword == entities*.keyword
        dto.keywordCounts*.count == entities*.count
    }

    def 'toKeywordListResponse()'() {

        given:
        def entities = Instancio.ofList(GroupedKeywordCountImpl.class)
            .size(3)
            .create()

        when:
        def dto = keywordCountMapper.toKeywordListResponse(entities)

        then:
        3 == dto.keywordCounts.size()
        entities*.keyword == dto.keywordCounts*.keyword
        entities*.totalCount == dto.keywordCounts*.count
    }

    def 'toKeywordListResponseWithEmpty()'() {

        given:
        def entities = null

        when:
        def dto = keywordCountMapper.toKeywordListResponse(entities)

        then:
        0 == dto.keywordCounts.size()
    }

    class GroupedKeywordCountImpl implements KeywordCountRepository.GroupedKeywordCount {

        String keyword

        Long totalCount

        @Override
        String getKeyword() {
            return keyword
        }

        @Override
        Integer getTotalCount() {
            return totalCount
        }

    }

}
