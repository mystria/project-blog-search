package com.mys.projectblogsearch;

import java.time.ZoneId;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Constants {

    public static final Integer KEYWORD_SIZE_LIMIT = 255;

    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Seoul");

}
