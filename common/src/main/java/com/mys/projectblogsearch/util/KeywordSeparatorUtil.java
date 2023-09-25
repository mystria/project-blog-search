package com.mys.projectblogsearch.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class KeywordSeparatorUtil {

    private static final String QUERY_DELIMITER = "\\s+";

    public static Stream<String> separateToStream(String keyword) {

        if (StringUtils.isBlank(keyword)) {
            return Stream.empty();
        }
        return Arrays.stream(keyword.trim().split(QUERY_DELIMITER));

    }

    public static List<String> separate(String keyword) {

        return separateToStream(keyword).toList();

    }

}
