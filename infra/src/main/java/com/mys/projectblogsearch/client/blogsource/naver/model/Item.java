package com.mys.projectblogsearch.client.blogsource.naver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String title;

    private String description;

    private String link;

    @JsonProperty("bloggername")
    private String bloggerName;

    @JsonProperty("postdate")
    private String postDate;

}
