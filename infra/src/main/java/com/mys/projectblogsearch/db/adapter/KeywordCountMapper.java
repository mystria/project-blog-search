package com.mys.projectblogsearch.db.adapter;

import com.mys.projectblogsearch.db.model.KeywordCountEntity;
import com.mys.projectblogsearch.db.repository.KeywordCountRepository.GroupedKeywordCount;
import com.mys.projectblogsearch.request.PortKeywordListRequest;
import com.mys.projectblogsearch.response.PortKeywordListResponse;
import com.mys.projectblogsearch.response.PortKeywordListResponse.KeywordCount;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KeywordCountMapper {

    KeywordCountMapper INSTANCE = Mappers.getMapper(KeywordCountMapper.class);

    default List<KeywordCountEntity> toKeywordCountEntityList(PortKeywordListRequest request) {

        return Optional.ofNullable(request)
            .map(PortKeywordListRequest::getKeywordCountList).orElse(List.of()).stream()
            .map(this::toKeywordCountEntity)
            .toList();
    }

    @Mapping(target = "id", ignore = true)
    KeywordCountEntity toKeywordCountEntity(PortKeywordListRequest.KeywordCount keywordCount);

    default PortKeywordListResponse toKeywordListResponse(List<GroupedKeywordCount> groupedKeywordCount) {

        return PortKeywordListResponse.builder()
            .keywordCountList(toKeywordCountList(groupedKeywordCount))
            .build();
    }

    default List<KeywordCount> toKeywordCountList(List<GroupedKeywordCount> groupedKeywordCount) {

        return Optional.ofNullable(groupedKeywordCount).orElse(List.of()).stream()
            .map(this::toKeywordCount)
            .toList();
    }

    @Mapping(target = "count", source = "totalCount")
    KeywordCount toKeywordCount(GroupedKeywordCount groupedKeywordCount);

}
