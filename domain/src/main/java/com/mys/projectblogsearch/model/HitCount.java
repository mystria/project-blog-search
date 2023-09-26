package com.mys.projectblogsearch.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HitCount {

    private final String keyword;

    private int count = 0;

    public HitCount hit() {

        this.count++;
        return this;

    }

    public String toString() {
        return keyword + "(" + count + ")";
    }

}
