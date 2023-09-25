package com.mys.projectblogsearch.util


import spock.lang.Specification

class KeywordSeparatorUtilTest extends Specification {

    def 'toBlogSearchRequest()'() {

        when:
        def keywordList = KeywordSeparatorUtil.separate(words_)

        then:
        size_ == keywordList.size()

        where:
        words_          | size_
        ""              | 0
        " "             | 0
        "   "           | 0
        "  A  "         | 1
        "가 sk 123"      | 3
        " A     B     " | 2
        " AAAAAAA "     | 1
        " A B C D E "   | 5
        " ㅁ_ ㅠㅠ  뷁"    | 3

    }

}
