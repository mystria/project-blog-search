package com.mys.projectblogsearch.db.adapter

import com.mys.projectblogsearch.db.repository.KeywordCountRepository
import com.mys.projectblogsearch.request.PortKeywordListRequest
import org.instancio.Instancio
import spock.lang.Specification
import spock.lang.Subject

class KeywordCountMapperTest extends Specification {

    @Subject
    KeywordCountMapper keywordCountMapper = KeywordCountMapper.INSTANCE

    def 'toKeywordCountEntityList()'() {

        given:
        def dto = Instancio.of(PortKeywordListRequest.class)
            .create()

        when:
        def entities = keywordCountMapper.toKeywordCountEntityList(dto)

        then:
        3 == entities.size()
        dto.keywordCountList*.keyword == entities*.keyword
        dto.keywordCountList*.count == entities*.count
    }

    def 'toKeywordListResponse()'() {

        given:
        def entities = Instancio.ofList(GroupedKeywordCountImpl.class)
            .size(3)
            .create()

        when:
        def dto = keywordCountMapper.toKeywordListResponse(entities)

        then:
        3 == dto.keywordCountList.size()
        entities*.keyword == dto.keywordCountList*.keyword
        entities*.totalCount == dto.keywordCountList*.count
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
