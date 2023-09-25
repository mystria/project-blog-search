package com.mys.projectblogsearch.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {

    ACCURACY("정확도순"),
    RECENCY("최신순"),
    ;

    private final String description;

}
