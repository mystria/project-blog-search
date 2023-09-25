package com.mys.projectblogsearch.client.blogsource.daum.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {

    ACCURACY("accuracy"),
    RECENCY("recency"),
    ;

    private final String value;

}
