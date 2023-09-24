package com.mys.projectblogsearch.v1.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@JsonPropertyOrder({"code", "message"})
@RequiredArgsConstructor
public abstract class DomainError {

    private final int code;

    private final String message;
}