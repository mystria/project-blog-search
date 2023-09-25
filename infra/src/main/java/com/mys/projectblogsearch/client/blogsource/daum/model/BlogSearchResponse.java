package com.mys.projectblogsearch.client.blogsource.daum.model;

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

    private Integer offset;

    private Integer limit;

    private Meta meta;

    private List<Document> documents;

}
