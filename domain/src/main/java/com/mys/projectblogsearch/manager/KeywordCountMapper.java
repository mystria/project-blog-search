package com.mys.projectblogsearch.manager;

import com.mys.projectblogsearch.response.PortKeywordListResponse;
import com.mys.projectblogsearch.response.UseCaseKeywordListResponse;
import java.util.List;
import java.util.stream.IntStream;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

@Mapper
public interface KeywordCountMapper {

    KeywordCountMapper INSTANCE = Mappers.getMapper(KeywordCountMapper.class);

    default UseCaseKeywordListResponse toUseCaseBlogListResponse(PortKeywordListResponse response) {

        if (CollectionUtils.isEmpty(response.getKeywordCounts()))
            return UseCaseKeywordListResponse.builder()
                .keywordCounts(List.of())
                .build();

        return UseCaseKeywordListResponse.builder()
            .keywordCounts(IntStream.range(0, response.getKeywordCounts().size())
                .mapToObj(i -> toKeywordCount(response.getKeywordCounts().get(i), i + 1))
                .toList())
            .build();

    }

    UseCaseKeywordListResponse.KeywordCount toKeywordCount(PortKeywordListResponse.KeywordCount keywordCount, Integer rank);

}
