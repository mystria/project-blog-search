package com.mys.projectblogsearch.client.blogsource;

import com.mys.projectblogsearch.Constants;
import com.mys.projectblogsearch.util.KeywordSeparatorUtil;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class QueryValidator {

    public static void validate(String query) {

        // This is a limitation of our database structure

        List<String> words = KeywordSeparatorUtil.separateToStream(query)
            .filter(word -> word.length() > Constants.KEYWORD_SIZE_LIMIT)
            .toList();

        if (!words.isEmpty()) {
            throw new IllegalArgumentException("Too long words: " + String.join(", ", words));
        }

    }

}
