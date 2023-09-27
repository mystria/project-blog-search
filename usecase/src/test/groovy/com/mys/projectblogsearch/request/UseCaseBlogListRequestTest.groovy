package com.mys.projectblogsearch.request

import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import spock.lang.Specification
import spock.lang.Subject

class UseCaseBlogListRequestTest extends Specification {

    @Subject
    UseCaseBlogListRequest request

    Validator validator

    def setup() {

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator()
        }

    }

    def 'validate() - query'() {

        given:
        def req = UseCaseBlogListRequest.builder()
            .query(query_)
            .offset(1)
            .limit(10)
            .build()

        when:
        def violations = validator.validate(req)

        then:
        size_ == violations.size()

        where:
        query_    | size_
        "123"     | 0
        null      | 1
        ""        | 1
        " "       | 1
        "A" * 255 | 0
        "A" * 256 | 0
        "A" * 999 | 0
        "가" * 255 | 0
        "가" * 256 | 0

    }

    def 'validate() - offset'() {

        given:
        def req = UseCaseBlogListRequest.builder()
            .query("A")
            .offset(offset_)
            .limit(10)
            .build()

        when:
        def violations = validator.validate(req)

        then:
        size_ == violations.size()

        where:
        offset_   | size_
        null      | 1
        0         | 1
        1         | 0
        10        | 0
        50        | 0
        51        | 1
        999999999 | 1

    }

    def 'validate() - limit'() {

        given:
        def req = UseCaseBlogListRequest.builder()
            .query("A")
            .offset(1)
            .limit(limit_)
            .build()

        when:
        def violations = validator.validate(req)

        then:
        size_ == violations.size()

        where:
        limit_    | size_
        null      | 1
        0         | 1
        1         | 0
        10        | 0
        50        | 0
        51        | 1
        999999999 | 1

    }

}
