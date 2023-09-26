package com.mys.projectblogsearch.client.blogsource.naver.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogSearchResponse {

    private Integer start;

    private Integer display;

    private Integer total;

    private List<Item> items;

}
