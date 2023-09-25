package com.mys.projectblogsearch.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VendorType {

    DAUM("Daum Blogs"),
    NAVER("Naver Blogs"),
    ;

    private final String decsription;

}
