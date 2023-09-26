package com.mys.projectblogsearch.client.blogsource.naver;

import com.mys.projectblogsearch.Constants;
import com.mys.projectblogsearch.client.blogsource.naver.model.BlogSearchRequest;
import com.mys.projectblogsearch.client.blogsource.naver.model.BlogSearchResponse;
import com.mys.projectblogsearch.client.blogsource.naver.model.Item;
import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import com.mys.projectblogsearch.response.PortBlogListResponse.BlogSummary;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NaverBlogMapper {

    int NAVER_DEFAULT_START = 1;

    int NAVER_DEFAULT_DISPLAY = 10;

    DateTimeFormatter NAVER_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    NaverBlogMapper INSTANCE = Mappers.getMapper(NaverBlogMapper.class);

    @Mapping(target = "display", source = "size")
    @Mapping(target = "start", expression = "java(toStart(request.getPage(), request.getSize()))")
    BlogSearchRequest toBlogSearchRequest(PortBlogListRequest request);

    default Integer toStart(Integer page, Integer size) {

        if (Objects.isNull(page) || page < 1)
            return null;
        size = Objects.isNull(size) ? NAVER_DEFAULT_DISPLAY : size;

        // start = (offset - 1) * size + 1
        return (page - 1) * size + 1;

    }

    @Mapping(target = "offset", expression = "java(toOffset(response.getStart(), response.getDisplay()))")
    @Mapping(target = "limit", source = "response.display")
    @Mapping(target = "totalCount", source = "response.total")
    @Mapping(target = "blogs", source = "response.items")
    PortBlogListResponse toPortBlogListResponse(BlogSearchResponse response);

    default Integer toOffset(Integer start, Integer display) {

        // start = (offset - 1) * display + 1
        // offset = (start - 1) / display + 1
        return (start - 1) / display + 1;

    }

    @Mapping(target = "contents", source = "description")
    @Mapping(target = "url", source = "link")
    @Mapping(target = "blogName", source = "bloggerName")
    @Mapping(target = "thumbnail", ignore = true)
    @Mapping(target = "dateTime", source = "postDate", qualifiedByName = "toZonedDateTime")
    BlogSummary toBlogSummary(Item item);

    @Named("toZonedDateTime")
    default ZonedDateTime toZonedDateTime(String postdate) {

        LocalDate localDate = LocalDate.parse(postdate, NAVER_DATE_FORMATTER);
        return ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, Constants.DEFAULT_ZONE_ID);

    }

}
