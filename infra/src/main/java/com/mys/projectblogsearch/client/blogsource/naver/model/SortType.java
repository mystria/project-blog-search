package com.mys.projectblogsearch.client.blogsource.naver.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {

    ACCURACY("sim"),
    RECENCY("date"),
    ;

    private final String value;

}
