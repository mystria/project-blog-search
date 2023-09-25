package com.mys.projectblogsearch.client.blogsource.daum.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    private String title;

    private String contents;

    private String url;

    @JsonProperty("blogname")
    private String blogName;

    private String thumbnail;

    @JsonProperty("datetime")
    private ZonedDateTime dateTime;

}

