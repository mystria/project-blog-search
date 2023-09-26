package com.mys.projectblogsearch.v1.controller;

import com.mys.projectblogsearch.response.UseCaseKeywordListResponse;
import com.mys.projectblogsearch.v1.response.KeywordListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KeywordCountMapper {

    KeywordCountMapper INSTANCE = Mappers.getMapper(KeywordCountMapper.class);

    KeywordListResponse toKeywordListResponse(UseCaseKeywordListResponse result);

}
